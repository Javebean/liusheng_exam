package com.liusheng.service;

import java.util.ArrayList;
import java.util.List;

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
		//keypoint实际上是【id,知识点】组合
		String keypointId = fb.getKeypointId();
		String[] split = keypointId.split(",");
		fb.setKeypoint(split[1]);
		fb.setKeypointId(split[0]);
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
		return fillDao.checkOneFillBlank(id);
	}
	
	public List<FillBlankAnswer> getFBAnswer(int fbId) {
		return fillDao.getFBAnswer(fbId);
	}
	
	public List<FillBlank> createFillBlank(String kpId[]){
		List<FillBlank> list =  fillDao.createFillBlankByKid(kpId);
		List<FillBlank> result = new ArrayList<FillBlank>();
		//从查出来的list中取出10个空,但是不一定是10个空
		int fillNums  = 0;
		for(FillBlank f: list){
			fillNums+=f.getFillNums();
			result.add(f);
			if(fillNums>=10){
				break;
			}
		}
		
		return result;
		
	}
	
}
