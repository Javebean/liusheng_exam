package com.liusheng.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.Keypoints;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.CreateWord;
import com.liusheng.util.NumberUtil;

@Service
public class SimpleSelectService {

	@Autowired
	private SimpleSelectDao ssDao;
	@Autowired
	private FillBlankService fservice;
	@Autowired
	private InterlocutionService iservice;
	@Autowired
	private KeypointsService kpservice;
	
	
	public boolean addOneSimpleSelection(SimpleSelection ss) {
		ss.setNumber(NumberUtil.createNum());
		String answer = ss.getAnswer();
		//根据答案标号 abcd,选择正确的答案
		String answerText=null;
		if("A".equalsIgnoreCase(answer)){
			answerText = ss.getOptionA();
		}else if("B".equalsIgnoreCase(answer)){
			answerText = ss.getOptionB();
		}else if("C".equalsIgnoreCase(answer)){
			answerText = ss.getOptionC();
		}else if("D".equalsIgnoreCase(answer)){
			answerText = ss.getOptionD();
		}
		ss.setAnswerText(answerText);
		
		//网页传过来的keypointId,实际上这【id，知识点】这种组合
		String keypointId = ss.getKeypointId();
		String keypoint = ss.getKeypoint();
		if(null!=keypointId && !"".equals(keypointId)){
			String[] split = keypointId.split(",");
			ss.setKeypoint(split[1]);
			ss.setKeypointId(split[0]);
		}else{
			//解析excel中的
			int kpid = kpservice.getKeypointByName(keypoint);
			if(kpid!=-1){
				ss.setKeypointId(kpid+"");
			}else{
				Keypoints k = new Keypoints(ss.getKeypoint(), NumberUtil.createNum());
				kpservice.addKeypoints(k);
				ss.setKeypointId(k.getId()+"");
			}
			
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

	public boolean checkOneSimpleSelection(int id) {
		return ssDao.checkOneSimpleSelection(id);
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
			ss = ssDao.createSimpleByKid(arr[i%arrLen]);
			if(ss!=null){
				list.add(ss);
			}
			
			if(i%arrLen==0&&i!=0){
				Collections.shuffle(Arrays.asList(arr));
			}
			
		}
		return list;
	}
	
	//
	public String createexamService(String simple,String fill,String inter){
		//单选题
		JSONArray simpleArr = new JSONArray(simple);
			int simpleLen = simpleArr.length();
			//存放传过来的知识点id
			String sarr[] = new String[simpleLen]; 
			for(int i=0;i<simpleLen;i++){
				String str = (String) simpleArr.get(i);
				sarr[i] = str;
			}
			List<SimpleSelection> createSimple = createSimple(sarr);
		
		
		
		
		//填空题
		JSONArray fillArr = new JSONArray(fill);
		int fillLen = fillArr.length();
		String fillKpIdArr[] = new String[fillLen];
		
		for(int i=0;i<fillLen;i++){
			fillKpIdArr[i] = fillArr.get(i).toString();
		}
		List<FillBlank> createFill = fservice.createFillBlank(fillKpIdArr);
		List<String> fillblankInfo = null;
		if(createFill.size()>0){
			fillblankInfo = new ArrayList<String>();
			for(FillBlank f:createFill){
				fillblankInfo.add(f.getProblem());
			}
		}
		
		//问答题
		JSONArray interArr = new JSONArray(fill);
		int interLen = interArr.length();
		String iArr[] = new String[interLen];//存放kpid的数组
		for(int i=0;i<interLen;i++){
			iArr[i] = (String) fillArr.get(i);
		}
		List<Interlocution> createInter = iservice.createInter(iArr);
		Map<String,Boolean> interInfo = null;
		if(createInter.size()>0){
			interInfo = new HashMap<String, Boolean>();
			for(Interlocution i :createInter){
				String pro = i.getProblem();
				String imgurl = i.getImgUrl();
				interInfo.put(pro, imgurl==null?true:false);
			}
			
		}
		
		try {
			CreateWord.createExam(createSimple, fillblankInfo, interInfo);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
