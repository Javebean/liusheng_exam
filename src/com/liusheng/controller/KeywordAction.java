package com.liusheng.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.CommonResult;
import com.liusheng.entities.Keypoints;
import com.liusheng.entities.Result;
import com.liusheng.service.KeypointsService;
@RestController
public class KeywordAction {

	Logger log = Logger.getLogger(KeywordAction.class);
	@Autowired
	private KeypointsService kservice;
	
	private String utf(String s){
		String str = "";
		try {
			str =  new String(s.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	@RequestMapping(value="/addkey/{keyName}")
	public CommonResult addKey(@PathVariable String keyName){
		keyName = utf(keyName);
		log.info("知识点 :： "+keyName);
		kservice.addKeypoints(new Keypoints(keyName));
		return new CommonResult(new Result(0, "增加知识点成功！"), null);
		
	}
	
	@RequestMapping(value="/pageskeypoint/{start}/{items}")
	public List<Keypoints> getPagesKeypoint(@PathVariable int start,@PathVariable int items){
		List<Keypoints> allKeypoints = kservice.getAllKeypoints(start, items);
		return allKeypoints;
	}
	
	@RequestMapping("/updatekey/{id}/{key}")
	public CommonResult updateKey(@PathVariable String id,@PathVariable String key){
		key = utf(key);
		log.info("更新知识点   id:"+id+" key"+key);
		Keypoints kp = new Keypoints(key);
		kp.setId(Integer.parseInt(id));
		boolean b =  kservice.updateKeypoints(kp);
		
		return new CommonResult(new Result(b?0:1, b?"知识点更新成功!":"知识点更新失败！"), key);
	}
	
	@RequestMapping("/deletekey/{id}")
	public int deleteKey(@PathVariable int id){
		return  kservice.deleteKeypoints(id);
	}
	
}
