package com.liusheng.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
import com.liusheng.entities.Keypoints;
import com.liusheng.util.NumberUtil;
@Service
public class FillBlankService {
	private Logger log = Logger.getLogger(FillBlankService.class);
	
	@Autowired
	private FillBlankDao fillDao;
	@Autowired
	private KeypointsService kpservice;
	
	public boolean addOneFillBlank(FillBlank fb) {
		//keypoint实际上是【id,知识点】组合
		String keypointId = fb.getKeypointId();
		String keypoint = fb.getKeypoint();
		if(null!=keypointId && !"".equals(keypointId)){
			String[] split = keypointId.split(",");
			fb.setKeypoint(split[1]);
			fb.setKeypointId(split[0]);
		}else{
			//解析excel中的
			int kpid = kpservice.getKeypointByName(keypoint);
			if(kpid!=-1){
				fb.setKeypointId(kpid+"");
			}else{
				Keypoints k = new Keypoints(keypoint, NumberUtil.createNum());
				kpservice.addKeypoints(k);
				fb.setKeypointId(k.getId()+"");
			}
		}
		fb.setNumber(NumberUtil.createNum());
		
		String problem = fb.getProblem();
		Pattern reg = Pattern.compile("(<)(\\W+?)(>)");
		Matcher matcher = reg.matcher(problem);
		StringBuilder answer = new StringBuilder();
		int fillnum = 0;
		while(matcher.find()){
			fillnum++;
			answer.append(matcher.group(2));
			answer.append(",");
		}
		//删掉最后一个逗号“，”
		
		answer.deleteCharAt(answer.length()-1);
		fb.setFillNums(fillnum);
		fb.setAnswer(answer.toString());
		return fillDao.addOneFillBlank(fb);
	}

	public boolean deleteOneFillBlank(int id) {
		return fillDao.deleteOneFillBlank(id);
	}

	public void updataOneFillBlank(FillBlank fb) {
	}

	public FillBlank getOneFillBlank(int id) {
		return null;
	}

	public List<FillBlank> getAllFillBlank(int page, int itemNums,int state) {
		int start = (page-1)*itemNums;
		return fillDao.getAllFillBlank(start, itemNums,state);
	}
	
	
	public int getFillBlankPageNums(int items){
		double count = fillDao.getFillBlankCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}

	public boolean checkOneFillBlank(int agreeId,String question,String keypoint,String keypointId) {
		keypointId = keypointId.substring(2);
		try {
			question = new String(question.getBytes("iso8859-1"),"utf-8");
			keypoint = new String(keypoint.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		return fillDao.checkOneFillBlank(agreeId,question,keypoint,keypointId);
	}
	
	public List<FillBlankAnswer> getFBAnswer(int fbId) {
		return fillDao.getFBAnswer(fbId);
	}
	
	public List<FillBlank> createFillBlank(String kpId[]){
		log.info("要出填空题知识点:"+Arrays.toString(kpId));
		Collections.shuffle(Arrays.asList(kpId));
		
		int arrLen = kpId.length;
		int i=0;
		FillBlank fb = null;
		int nums=0;//记录空格数量
		List<FillBlank> result = new ArrayList<FillBlank>();
		for(;i<10;i++){
			if(i!=0&&i%arrLen==0){
				Collections.shuffle(Arrays.asList(kpId));
			}
			fb = fillDao.createFillBlankByKid(kpId[i%arrLen]);
			log.info("查到的填空题:"+fb);
			if(null!=fb){
				result.add(fb);
				nums+=fb.getFillNums();
				if(nums>=10){
					//如果空大于等于10了。结束循环
					break;
				}
			}
		}
		return result;
		
	}
	
}
