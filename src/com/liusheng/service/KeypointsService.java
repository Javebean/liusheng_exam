package com.liusheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.KeypointsDao;
import com.liusheng.entities.Keypoints;

@Service
public class KeypointsService {

	@Autowired
	private KeypointsDao kDao;

	public void addKeypoints(Keypoints kp) {
		kDao.addKeypoints(kp);
	}

	public int deleteKeypoints(int id) {
		return kDao.deleteKeypoints(id);
	}

	public boolean updateKeypoints(Keypoints kp) {
		return kDao.updateKeypoints(kp);

	}

	public Keypoints getOneKeypoints(int id) {
		return null;
	}

	public List<Keypoints> getAllKeypoints(int start, int items) {
		List<Keypoints> allKeypoints = kDao.getAllKeypoints(start, items);
		return allKeypoints;
	}
	
	public List<Keypoints> getAllkp(){
		return kDao.getAllkp();
	}
	
}
