package com.liusheng.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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
	
	public List<Keypoints> getkeyfromkeypoint(){
		List<Keypoints> allkp = kDao.getAllkp();
		return allkp;
	}
	
	public String getAllkp(){
		List<Object[]> simpleAllkp = kDao.getSimpleAllkp();
		List<Object[]> fillBlankAllkp = kDao.getFillBlankAllkp();
		List<Object[]> interlocaionAllkp = kDao.getInterlocaionAllkp();
		
		JSONArray array = new JSONArray();
		array.put(getJsonStr(simpleAllkp));
		array.put(getJsonStr(fillBlankAllkp));
		array.put(getJsonStr(interlocaionAllkp));
		return array.toString();
	}
	
	private JSONArray getJsonStr(List<Object[]> list){
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		for(Object[] o : list){
			obj = new JSONObject();
			obj.put("keypointId", o[0].toString());
			obj.put("keypoint", o[1].toString());
			arr.put(obj);
		}
		return arr;
	}
	
	public int getkpPages(int items){
		double count =  kDao.getkeypointCount();
		int pages = (int) Math.ceil(count/items);
		log.info("查询知识点一共 "+pages+" 页");
		return pages;
	}
	
	public int getKeypointByName(String name){
		Keypoints k = kDao.getKeypointByName(name);
		if(null!=k){
			return k.getId();
		}else{
			return -1;
		}
		
	}
	
}
