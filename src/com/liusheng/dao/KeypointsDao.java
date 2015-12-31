package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Keypoints;

public interface KeypointsDao {

	public void addKeypoints(Keypoints kp);
	
	public int deleteKeypoints(int id);
	
	public boolean updateKeypoints(Keypoints kp);
	
	public Keypoints getOneKeypoints(int id);
	
	public List<Keypoints> getAllKeypoints(int start,int items);
}
