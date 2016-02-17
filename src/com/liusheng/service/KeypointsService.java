package com.liusheng.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.KeypointsDao;
import com.liusheng.entities.Keypoints;

@Service
public class KeypointsService {

	private static Logger log = Logger.getLogger(KeypointsService.class);
	@Autowired
	private KeypointsDao kDao;

	public void addKeypoints(Keypoints kp) {
		kDao.addKeypoints(kp);
	}

	public boolean deleteKeypoints(int id) {
		return kDao.deleteKeypoints(id);
	}

	public boolean updateKeypoints(Keypoints kp) {
		return kDao.updateKeypoints(kp);

	}

	public Keypoints getOneKeypoints(int id) {
		return null;
	}

	public List<Keypoints> getAllKeypoints(int page, int items) {
		int start = (page-1)*items;
		List<Keypoints> allKeypoints = kDao.getAllKeypoints(start, items);
		return allKeypoints;
	}
	
	public List<Keypoints> getAllkp(){
		return kDao.getAllkp();
	}
	
	public int getkpPages(int items){
		double count =  kDao.getkeypointCount();
		int pages = (int) Math.ceil(count/items);
		log.info("查询知识点一共 "+pages+" 页");
		return pages;
	}
	
}
