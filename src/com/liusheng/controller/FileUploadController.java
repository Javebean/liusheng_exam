package com.liusheng.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.entities.CommonResult;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.Result;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.service.FillBlankService;
import com.liusheng.service.InterlocutionService;
import com.liusheng.service.SimpleSelectService;
import com.liusheng.util.AnalyzeExcel;
import com.liusheng.util.Constant;
import com.liusheng.util.Tools;

@Controller
public class FileUploadController {

	Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	private ServletContext context;
	@Autowired
	private SimpleSelectService simpleService;
	@Autowired
	private FillBlankService fillService;
	@Autowired
	private InterlocutionService interService;
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("type") String type,
			@RequestParam("file") MultipartFile file) {
		CommonResult cr = null;
		if (!file.isEmpty()) {
			logger.info("开始上传。。。");
			try {
				logger.info("上传路径为：" + context.getRealPath(""));
				logger.info("原始名称：" + file.getOriginalFilename());
				String originalFilename = file.getOriginalFilename();
				String suffix = originalFilename.substring(originalFilename
						.lastIndexOf(".") + 1);
				logger.info("上传文件的后缀名：" + suffix);

				if ("xls".equals(suffix) || "xlsx".equals(suffix)) {
					String filename = UUID.randomUUID().toString();
					byte[] bytes = file.getBytes();

					String filepath = context.getRealPath("") + "/file/"
							+ filename + "." + suffix;

					logger.info("上传的完整路径-" + filepath);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(filepath)));
					stream.write(bytes);
					stream.close();

					if ("0".endsWith(type)) {
						// 混合上传
						for (int i = 0; i < 3; i++) {
							List<List<String>> results = AnalyzeExcel
									.analyzeExcel(i, filepath);

							for (List<String> result : results) {
								for (String r : result) {
									System.out.print(r);
								}
								System.out.println();
							}
						}

					} else if ("1".endsWith(type)) {
						//单选题
						List<List<String>> results = AnalyzeExcel.analyzeExcel(
								0, filepath);

						for (List<String> result : results) {
							SimpleSelection s = new SimpleSelection();
							s.setNumber(Tools.createNum());
							s.setProblem(result.get(0));
							s.setOptionA(result.get(1));
							s.setOptionB(result.get(2));
							s.setOptionC(result.get(3));
							s.setOptionD(result.get(4));
							s.setAnswer(result.get(5));
							s.setKeypoint(result.get(6));
							s.setCheckStatus(Constant.NO_CHECK);
							simpleService.addOneSimpleSelection(s);
						}
						
						
						
					}  else if ("2".endsWith(type)) {
						//填空题
						List<List<String>> results = AnalyzeExcel.analyzeExcel(
								0, filepath);

						for (List<String> result : results) {
							FillBlank f = new FillBlank();
							f.setCheckStatus(Constant.NO_CHECK);
							f.setProblem(result.get(0));
							f.setKeypoint(result.get(1));
							fillService.addOneFillBlank(f);
						}
					} else if ("3".endsWith(type)) {
						//问答题
						List<List<String>> results = AnalyzeExcel.analyzeExcel(
								0, filepath);

						for (List<String> result : results) {
							Interlocution i = new Interlocution();
							i.setProblem(result.get(0));
							i.setAnswer(result.get(1));
							i.setKeypoint(result.get(2));
							i.setCheckStatus(Constant.NO_CHECK);
							interService.addOneInterlocution(i, null, null);
							//通过excel中不允许上传带图片的图片
							
						}
					}

					logger.info("上传成功");
					cr = new CommonResult(new Result(0, "上传成功！"), null);

				} else {
					cr = new CommonResult(new Result(1, "请上传Excel文件！"), null);
				}

			} catch (Exception e) {
				e.printStackTrace();
				//return new CommonResult(new Result(1, "上传文件出错，请检查试题类型！"), null);
			}
		} else {
			logger.info("文件为空。。。");
			cr = new CommonResult(new Result(1, "文件名为空！"), null);
		}
		//return cr;
		return "uploadState";
	}
}