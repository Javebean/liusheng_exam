package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Keypoints;

public interface KeypointsDao {

	public void addKeypoints(Keypoints kp);
	
	public void deleteKeypoints(int id);
	
	public void updateKeypoints(Keypoints kp);
	
	public Keypoints getOneKeypoints(int id);
	
	public List<Keypoints> getAllKeypoints();
}
