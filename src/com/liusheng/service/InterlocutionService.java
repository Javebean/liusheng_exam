package com.liusheng.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.dao.InterlocutionDao;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.Keypoints;
import com.liusheng.util.NumberUtil;

@Service
public class InterlocutionService {

	@Autowired
	private InterlocutionDao iDao;

	private Logger log = Logger.getLogger(InterlocutionService.class);
	@Autowired
	private KeypointsService kpservice;
	public boolean addOneInterlocution(Interlocution il, MultipartFile mf,ServletContext context) {

		// 获取上传路径baseUrl
		il.setNumber(NumberUtil.createNum());
		if (null != mf) {
			String baseUrl = context.getRealPath("") + "\\uploadfile\\";
			
			Path path = Paths.get(baseUrl);
			if(Files.notExists(path)){
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String imgUrl = "";
			String fileName = mf.getOriginalFilename();
			if (!"".equalsIgnoreCase(fileName)) {
				// 传到服务器文件夹中的文件名称为 ctimeMillis+3个随机整数+原始文件名.suffix
				Long currentTimeMillis = System.currentTimeMillis();
				StringBuilder sb = new StringBuilder(
						currentTimeMillis.toString());
				for (int i = 0; i < 3; i++) {
					sb.append((int) (Math.random() * 10));
				}

				// 获取后缀
				String suffix = fileName.substring(fileName.indexOf("."),
						fileName.length());

				sb.append(suffix);
				if (fileName.endsWith(".png") || fileName.endsWith(".jpg")
						|| fileName.endsWith(".gif")
						|| fileName.endsWith(".jpeg")) {

					// 上传的是图片
					imgUrl = "uploadfile/" + sb;

				}
				try {
					mf.transferTo(new File(baseUrl + sb));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			il.setImgUrl(imgUrl);
			return iDao.addOneInterlocution(il);
		}else{
			//网页传过来的keypointId,实际上这【id，知识点】这种组合
			String keypointId = il.getKeypointId();
			String keypoint = il.getKeypoint();
			if(null==keypointId || keypointId.isEmpty()){
				//解析excel中的
				int kpid = kpservice.getKeypointByName(keypoint);
				if(kpid!=-1){
					il.setKeypointId(kpid+"");
				}else{
					Keypoints k = new Keypoints(keypoint, NumberUtil.createNum());
					kpservice.addKeypoints(k);
					il.setKeypointId(k.getId()+"");
				}
				
			}
			il.setImgUrl(null);
			return iDao.addOneInterlocution(il);
			
		}
	}

	public boolean deleteOneInterlocution(int id) {
		return iDao.deleteOneInterlocution(id);
	}

	public void updataOneInterlocution(Interlocution ss) {
	}

	public Interlocution getOneInterlocution(int id) {
		return null;
	}

	public List<Interlocution> getAllInterlocution(int page, int itemNums,int state) {
		int start = (page-1)*itemNums;
		return iDao.getAllInterlocution(start, itemNums,state);
	}

	public int geteInterLocutionPageNums(int items){
		double count = iDao.getInterlocaionCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}
	
	public boolean checkOneInterlocution(int agreeId,String question,String answer,String keypoint,String keypointId) {
		keypointId = keypointId.substring(2);
		try {
			question = new String(question.getBytes("iso8859-1"),"utf-8");
			answer = new String(answer.getBytes("iso8859-1"),"utf-8");
			keypoint = new String(keypoint.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return iDao.checkOneInterlocution(agreeId,question,answer, keypoint, keypointId);
	}

	public List<Interlocution> createInter(String [] kpids) {
		/**
		 * 1.查询知识点1 kid 的集合 daoImpl里面写的 2.从中随机选择一定数量的题目
		 * 
		 * */
		log.info("要出的问答题知识点Id:"+Arrays.toString(kpids));
		Collections.shuffle(Arrays.asList(kpids));
		int arrLen = kpids.length;
		int i=0;
		Interlocution il = null;
		int nums =5; //问答题的数量
		List<Interlocution> result = new ArrayList<Interlocution>();
		for(;i<nums;i++){
			if(i%arrLen==0&&i!=0){
				Collections.shuffle(Arrays.asList(kpids));
			}
			il = iDao.createInterlocaionByKid(kpids[i%arrLen]);
			log.info("查到的问答题目:"+il);
			if(null!=il){
				result.add(il);
			}
			
		}
		
		return result;
	}
}
