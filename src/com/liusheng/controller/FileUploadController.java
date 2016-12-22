package com.liusheng.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.service.FillBlankService;
import com.liusheng.service.InterlocutionService;
import com.liusheng.service.SimpleSelectService;
import com.liusheng.util.AnalyzeExcel;
import com.liusheng.util.Constant;
import com.liusheng.util.RESCODE;
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

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("type") int type,
			@RequestParam("file") MultipartFile file) {
		JSONObject  json = null ;
		logger.info("原始名称：" + file.getOriginalFilename());
		String originalFilename = file.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename
				.lastIndexOf(".") + 1);
		logger.info("上传文件的后缀名：" + suffix);

		if ("xls".equals(suffix) || "xlsx".equals(suffix)) {
			String filename = UUID.randomUUID().toString();

			String baseurl = Constant.UPLOAD_EXCEL_URL;
			Path path = Paths.get(baseurl);
			if(Files.notExists(path)){
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String filepath = baseurl +File.separator +filename + "." + suffix;

			logger.info("上传的完整路径-" + filepath);
			try {
				file.transferTo(new File(filepath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}//上传完成

			try {
				String jsonstr = AnalyzeExcel.analyzeExcel(
						0, filepath);
				json = new JSONObject(jsonstr);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			int code = json.getInt(Constant.RESPONSE_CODE_KEY);
			if(RESCODE.SUCCESS.getCode()!=code){
				return json.toString();
			}
			
			JSONArray data =(JSONArray) json.get(Constant.RESPONSE_DATA_KEY);
			Iterator<Object> iterator = data.iterator();
			
			if (type==0) {
				// 混合上传
			} else if (1==type) {
				//单选题
				//检查excel中的列数对不对。单选题有7列
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					if(result.length()!=7){
						json.remove(Constant.RESPONSE_DATA_KEY);
						json.put(Constant.RESPONSE_CODE_KEY, RESCODE.EXCEL_COLUMN_ERROR);
						json.put(Constant.RESPONSE_MSG_KEY, RESCODE.EXCEL_COLUMN_ERROR.getMsg());
						return json.toString();
					}
				}
				
				iterator = data.iterator();
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					SimpleSelection s = new SimpleSelection();
					s.setNumber(Tools.createNum());
					s.setProblem(result.getString(0));
					s.setOptionA(result.getString(1));
					s.setOptionB(result.getString(2));
					s.setOptionC(result.getString(3));
					s.setOptionD(result.getString(4));
					s.setAnswer(result.getString(5));
					s.setKeypoint(result.getString(6));
					s.setCheckStatus(Constant.NO_CHECK);
					simpleService.addOneSimpleSelection(s);
				}
				
			}  else if (2==type) {
				//填空题
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					if(result.length()!=2){
						json.remove(Constant.RESPONSE_DATA_KEY);
						json.put(Constant.RESPONSE_CODE_KEY, RESCODE.EXCEL_COLUMN_ERROR);
						json.put(Constant.RESPONSE_MSG_KEY, RESCODE.EXCEL_COLUMN_ERROR.getMsg());
						return json.toString();
					}
				}
				
				iterator = data.iterator();
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					FillBlank f = new FillBlank();
					f.setCheckStatus(Constant.NO_CHECK);
					f.setProblem(result.getString(0));
					f.setKeypoint(result.getString(1));
					fillService.addOneFillBlank(f);
				}
				
			} else if (3==type) {
				//问答题
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					if(result.length()!=3){
						json.remove(Constant.RESPONSE_DATA_KEY);
						json.put(Constant.RESPONSE_CODE_KEY, RESCODE.EXCEL_COLUMN_ERROR);
						json.put(Constant.RESPONSE_MSG_KEY, RESCODE.EXCEL_COLUMN_ERROR.getMsg());
						return json.toString();
					}
				}
				
				iterator = data.iterator();
				while(iterator.hasNext()){
					JSONArray result = (JSONArray) iterator.next();
					Interlocution i = new Interlocution();
					i.setProblem(result.getString(0));
					i.setAnswer(result.getString(1));
					i.setKeypoint(result.getString(2));
					i.setCheckStatus(Constant.NO_CHECK);
					interService.addOneInterlocution(i, null, null);
					//通过excel中不允许上传带图片的图片
				}
				
			}
			logger.info("上传成功");

		} else {
			json = new JSONObject();
			json.put(Constant.RESPONSE_CODE_KEY, RESCODE.UPLOAD_FILE_TYPE_ERROR);
			json.put(Constant.RESPONSE_MSG_KEY, RESCODE.UPLOAD_FILE_TYPE_ERROR.getMsg());
			return json.toString();
		}
		
		json.remove(Constant.RESPONSE_DATA_KEY);
		json.put(Constant.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
		return json.toString();
	}
}