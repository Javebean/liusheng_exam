package com.liusheng.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.Tools;

@Service
public class SimpleSelectService {
	
	private Logger log = Logger.getLogger(SimpleSelectService.class);
	
	@Autowired
	private SimpleSelectDao ssDao;
	@Autowired
	private FillBlankService fservice;
	@Autowired
	private InterlocutionService iservice;
	@Autowired
	private KeypointsService kpservice;
	
	
	public boolean addOneSimpleSelection(SimpleSelection ss) {
		ss.setNumber(Tools.createNum());
		
		String answer = ss.getAnswer().toUpperCase();
		ss.setAnswer(answer);
		//根据答案标号 abcd,选择正确的答案
		String answerText=null;
		if("A".equals(answer)){
			answerText = ss.getOptionA();
		}else if("B".equals(answer)){
			answerText = ss.getOptionB();
		}else if("C".equals(answer)){
			answerText = ss.getOptionC();
		}else if("D".equals(answer)){
			answerText = ss.getOptionD();
		}
		ss.setAnswerText(answerText);
		
		//网页传过来的keypointId,keypoint
		String keypointId = ss.getKeypointId();
		String keypoint = ss.getKeypoint();
		if(null!=keypointId || "".equals(keypointId)){
			//解析excel中的
			int kpid = kpservice.getKeypointByName(keypoint);
			log.info("是否查到相同的知识点："+kpid);
			if(kpid!=-1){
				ss.setKeypointId(String.valueOf(kpid));
			}else{
				return false;
			}
			
		}
		
		if(ss.getId()!=0){
			ss.setCheckStatus(1);
		}
		
		return ssDao.addOneSimpleSelection(ss);
	}

	public boolean deleteOneSimpleSelection(int id) {
		return ssDao.deleteOneSimpleSelection(id);
	}

	public void updataOneSimpleSelection(SimpleSelection ss) {
	}

	public SimpleSelection getOneSimpleSelection(int id) {
		return null;
	}

	public List<SimpleSelection> getAllSimpleSelection(int page, int itemNums ,int state) {
		int start = (page-1)*itemNums;
		return ssDao.getAllSimpleSelection(start, itemNums,state);
	}

	public boolean checkOneSimpleSelection(int agreeId,String question,String option,String keypoint,String optionSy,String keypointId) {
		return ssDao.checkOneSimpleSelection(agreeId,question, option, keypoint,optionSy,keypointId);
	}
	
	public int getsimplecount(int items){
		double count = ssDao.getSimpleSelectionCount();
		int page = (int) Math.ceil(count/items);
		return page;
	}
	
	public List<SimpleSelection> createSimple(String []arr){
		/**
			arr:要出的单选题知识点id，
			先对arr.随机排序，然后依次取题
			然后在随机排序。在依次取题，取满10题为止
		 * 
		 * */
		
		Collections.shuffle(Arrays.asList(arr));
		System.out.println("第一次洗牌："+Arrays.toString(arr));
		List<SimpleSelection> list = new ArrayList<SimpleSelection>();
		SimpleSelection ss = null;
		int i=0;
		int arrLen = arr.length;
		for(;i<10;i++){
			//第一遍出题完重新洗牌
			if(i!=0&&i%arrLen==0){
				Collections.shuffle(Arrays.asList(arr));
			}
			
			ss = ssDao.createSimpleByKid(arr[i%arrLen]);
			if(ss!=null){
				list.add(ss);
			}
			
			
		}
		return list;
	}
	
}
