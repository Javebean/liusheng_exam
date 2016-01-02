package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
import com.liusheng.entities.SimpleSelection;

public interface FillBlankDao {

	public void addOneFillBlank(FillBlank fb);

	public void deleteOneFillBlank(int id);

	public void updataOneFillBlank(FillBlank fb);

	public FillBlank getOneFillBlank(int id);

	public List<FillBlank> getAllFillBlank(int start, int itemNums);

	public boolean checkOneFillBlank(int id);
	
	public List<FillBlankAnswer> getFBAnswer(int fbId);
	
	public FillBlank createFillBlankByKid(String kpId , int romdom);
	
	//查询属于该知识点的单选题的数量
	public long getFillBlankCount(String kpId );
	
}
