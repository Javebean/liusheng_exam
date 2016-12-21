package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;

public interface FillBlankDao {

	public boolean addOneFillBlank(FillBlank fb);

	public boolean deleteOneFillBlank(int id);

	public void updataOneFillBlank(FillBlank fb);

	public FillBlank getOneFillBlank(int id);

	public List<FillBlank> getAllFillBlank(int start, int itemNums,int state);
	
	public long getFillBlankCount();

	public boolean checkOneFillBlank(int agreeId,String question,String keypoint,String keypointId);
	
	public List<FillBlankAnswer> getFBAnswer(int fbId);
	
	public FillBlank createFillBlankByKid(String kpId);
	
	//查询属于该知识点的单选题的数量
	public long getFillBlankCount(String kpId );
	
	public Object[] getFillBlankCountByName(String name[]);
	
}
