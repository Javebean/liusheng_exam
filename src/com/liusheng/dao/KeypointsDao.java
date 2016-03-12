package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Keypoints;
import com.liusheng.entities.SimpleSelection;

public interface KeypointsDao {

	public void addKeypoints(Keypoints kp);
	
	public boolean deleteKeypoints(int id);
	
	public boolean updateKeypoints(Keypoints kp);
	
	public Keypoints getOneKeypoints(int id);
	
	public List<Keypoints> getAllKeypoints(int start,int items);
	
	public long getkeypointCount();
	
	//此接口废弃
	public List<Keypoints> getAllkp();
	
	//查询单选题中包含的知识点id、知识点文字
	public List<Object[]> getSimpleAllkp();
	
	//查询填空题中包含的知识点id、知识点文字
	public List<Object[]> getFillBlankAllkp();
	//查询问答题中包含的知识点id、知识点文字
	public List<Object[]> getInterlocaionAllkp();
	
	//作用于excel上传的时候，excel填的知识点如果存在，就不用插入了
	public Keypoints getKeypointByName(String name);
}
