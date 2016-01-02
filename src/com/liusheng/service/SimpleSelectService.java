package com.liusheng.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;

@Service
public class SimpleSelectService {

	@Autowired
	private SimpleSelectDao ssDao;
	@Autowired
	private FillBlankService fservice;
	@Autowired
	private InterlocutionService iservice;
	
	public void addOneSimpleSelection(SimpleSelection ss) {
		ssDao.addOneSimpleSelection(ss);
	}

	public boolean deleteOneSimpleSelection(int id) {
		return ssDao.deleteOneSimpleSelection(id);
	}

	public void updataOneSimpleSelection(SimpleSelection ss) {
	}

	public SimpleSelection getOneSimpleSelection(int id) {
		return null;
	}

	public List<SimpleSelection> getAllSimpleSelection(int start, int itemNums) {
		return ssDao.getAllSimpleSelection(start, itemNums);
	}

	public boolean checkOneSimpleSelection(int id) {
		return ssDao.checkOneSimpleSelection(id);
	}
	
	
	
	public List<SimpleSelection> createSimple(Map<String,Integer> map){
		/**
		 * 1.查询知识点1 kid 的集合  daoImpl里面写的
		 * 2.从中随机选择一定数量的题目
		 * 
		 * */
		List<SimpleSelection> list = new ArrayList<SimpleSelection>();
		Random random = new Random();
		SimpleSelection ss = null;
		for(Map.Entry<String, Integer> m : map.entrySet()){
			String kpId = m.getKey();
			//该知识点要出的数量
			Integer nums = m.getValue();
			int count =  (int) ssDao.getSimpleSelectionCount(kpId);
			for(int i=0;i<nums;i++){
				 ss = ssDao.createSimpleByKid(kpId, random.nextInt(count));
				 list.add(ss);
			}
		}
		return list;
	}
	
	//
	public String createexamService(String simple,String fill,String inter){
		JSONArray simpleArr = new JSONArray(simple);
		int simpleLen = simpleArr.length();
		Map<String,Integer> simpleMap = new HashMap<String, Integer>();
		for(int i=0;i<simpleLen;i++){
			String str = (String) simpleArr.get(i);
			String arr[] = str.split("#");
			simpleMap.put(arr[0], Integer.parseInt(arr[1]));
			List<SimpleSelection> createSimple = createSimple(simpleMap);
			System.out.println("+++"+createSimple);
		}
		
		
		JSONArray fillArr = new JSONArray(fill);
		int fillLen = fillArr.length();
		Map<String,Integer> fillMap = new HashMap<String, Integer>();
		for(int i=0;i<fillLen;i++){
			String str = (String) fillArr.get(i);
			String arr[] = str.split("#");
			fillMap.put(arr[0], Integer.parseInt(arr[1]));
			List<FillBlank> createFill = fservice.createFillBlank(fillMap);
			System.out.println("---"+createFill);
		}
		
		
		JSONArray interArr = new JSONArray(fill);
		int interLen = interArr.length();
		Map<String,Integer> interMap = new HashMap<String, Integer>();
		for(int i=0;i<interLen;i++){
			String str = (String) fillArr.get(i);
			String arr[] = str.split("#");
			interMap.put(arr[0], Integer.parseInt(arr[1]));
			List<Interlocution> createInter = iservice.createInter(interMap);
			System.out.println("***"+createInter);
		}
		
		return null;
	}
}
