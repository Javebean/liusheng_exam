package com.liusheng.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.CommonResult;
import com.liusheng.entities.Keypoints;
import com.liusheng.entities.Result;
import com.liusheng.service.KeypointsService;
import com.liusheng.util.NumberUtil;
@RestController
public class KeywordAction {

	Logger log = Logger.getLogger(KeywordAction.class);
	@Autowired
	private KeypointsService kservice;
	
	/*private String utf(String s){
		String str = "";
		try {
			str =  new String(s.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;	
	}*/
	
	
	@RequestMapping(value="/addkey")
	public CommonResult addKey(@RequestParam(value="keyname") String keyName){
		log.info("keyword "+keyName);
		kservice.addKeypoints(new Keypoints(keyName,NumberUtil.createNum()));
		return new CommonResult(new Result(0, "增加知识点成功！"), null);
		
	}
	
	@RequestMapping(value="/pageskeypoint/{page}/{items}")
	public List<Keypoints> getPagesKeypoint(@PathVariable int page,@PathVariable int items){
		List<Keypoints> allKeypoints = kservice.getAllKeypoints(page, items);
		return allKeypoints;
	}
	
	@RequestMapping(value="/getkpages")
	public int getkeypointPageNums(int items){
		return kservice.getkpPages(items);
	}
	
	
	@RequestMapping(value="/getallkp",produces="text/html;charset=utf-8")
	public String getAllkeyPoint(){
		return kservice.getAllkp();
	}
	
	@RequestMapping(value="/getallkpfromkp")
	public List<Keypoints> getAllkeyPointfromkp(){
		return kservice.getkeyfromkeypoint();
	}
	
	@RequestMapping("/updatekey/{id}")
	public CommonResult updateKey(@PathVariable String id, String key){
		log.info("更新知识点   id:"+id+" key"+key);
		Keypoints kp = new Keypoints(key,NumberUtil.createNum());
		kp.setId(Integer.parseInt(id));
		boolean b =  kservice.updateKeypoints(kp);
		
		return new CommonResult(new Result(b?0:1, b?"知识点更新成功!":"知识点更新失败！"), key);
	}
	
	@RequestMapping("/deletekey/{id}")
	public boolean deleteKey(@PathVariable int id){
		return  kservice.deleteKeypoints(id);
	}
	
}
