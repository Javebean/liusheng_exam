package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Keypoints;

public interface KeypointsDao {

	public void addKeypoints(Keypoints kp);
	
	public boolean deleteKeypoints(int id);
	
	public boolean updateKeypoints(Keypoints kp);
	
	public Keypoints getOneKeypoints(int id);
	
	public List<Keypoints> getAllKeypoints(int start,int items);
	
	public long getkeypointCount();
	
	public List<Keypoints> getAllkp();
	
	//作用于excel上传的时候，excel填的知识点如果存在，就不用插入了
	public Keypoints getKeypointByName(String name);
}
