package com.liusheng.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.CreateWord;

@Service
public class SimpleSelectService {

	@Autowired
	private SimpleSelectDao ssDao;
	@Autowired
	private FillBlankService fservice;
	@Autowired
	private InterlocutionService iservice;
	
	public boolean addOneSimpleSelection(SimpleSelection ss) {
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
	
	public List<SimpleSelection> createSimple(Map<String,Integer> map){
		/**
		 * 1.查询知识点1 kid 的集合  daoImpl里面写的
		 * 2.从中随机选择一定数量的题目
		 * 
		 * */
		List<SimpleSelection> list = new ArrayList<SimpleSelection>();
		List<SimpleSelection> ss = null;
		for(Map.Entry<String, Integer> m : map.entrySet()){
			String kpId = m.getKey();
			System.out.println("知识点Id:"+kpId);
			//该知识点要出的数量
			Integer nums = m.getValue();
			//该知识点有>0的题目数量
			ss = ssDao.createSimpleByKid(kpId, nums);
			list.addAll(ss);
		}
		return list;
	}
	
	//
	public String createexamService(String simple,String fill,String inter){
		//单选题
		JSONArray simpleArr = new JSONArray(simple);
		int simpleLen = simpleArr.length();
		Map<String,Integer> simpleMap = new HashMap<String, Integer>();
		for(int i=0;i<simpleLen;i++){
			String str = (String) simpleArr.get(i);
			String arr[] = str.split("#");
			simpleMap.put(arr[0], Integer.parseInt(arr[1]));
		}
		List<SimpleSelection> createSimple = createSimple(simpleMap);
		Map<String ,List<String>> simpleInfo = null;
		if(createSimple.size()>0){
			simpleInfo = new HashMap<String, List<String>>();
			List<String> list = null;
			for(SimpleSelection s : createSimple){
				list = new ArrayList<String>();
				list.add(s.getOptionA());
				list.add(s.getOptionB());
				list.add(s.getOptionC());
				list.add(s.getOptionD());
				simpleInfo.put(s.getProblem(), list);
			}
			
		}
		
		
		
		
		//填空题
		//填空题传过来不用传该知识点的题目数量，以为每个填空题 空 不确定，
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
		
		
		JSONArray interArr = new JSONArray(fill);
		int interLen = interArr.length();
		Map<String,Integer> interMap = new HashMap<String, Integer>();
		for(int i=0;i<interLen;i++){
			String str = (String) fillArr.get(i);
			String arr[] = str.split("#");
			interMap.put(arr[0], Integer.parseInt(arr[1]));
		}
		List<Interlocution> createInter = iservice.createInter(interMap);
		Map<String,Boolean> interInfo = null;
		if(createInter.size()>0){
			interInfo = new HashMap<String, Boolean>();
			for(Interlocution i :createInter){
				interInfo.put(i.getProblem(), i.getImgUrl()==null?true:false);
			}
			
		}
		
		try {
			CreateWord.createExam(simpleInfo, fillblankInfo, interInfo);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
