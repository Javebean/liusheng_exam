package com.liusheng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
@Service
public class FillBlankService {
	@Autowired
	private FillBlankDao fillDao;
	public boolean addOneFillBlank(FillBlank fb) {
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

	public boolean checkOneFillBlank(int id) {
		return false;
	}
	
	public List<FillBlankAnswer> getFBAnswer(int fbId) {
		return fillDao.getFBAnswer(fbId);
	}
	
	public List<FillBlank> createFillBlank(Map<String,Integer> map){
		/**
		 * 1.查询知识点1 kid 的集合  daoImpl里面写的
		 * 2.从中随机选择一定数量的题目
		 * 
		 * */
		List<FillBlank> list = new ArrayList<FillBlank>();
		Random random = new Random();
		FillBlank ss = null;
		for(Map.Entry<String, Integer> m : map.entrySet()){
			String kpId = m.getKey();
			//该知识点要出的数量
			Integer nums = m.getValue();
			int count =  (int) fillDao.getFillBlankCount(kpId);
			
			if(count>0){
				//该知识点有>0的题目数量
				for(int i=0;i<nums;i++){
					ss = fillDao.createFillBlankByKid(kpId, random.nextInt(count));
					list.add(ss);
				}
				
			}
		}
		return list;
	}
	
}
