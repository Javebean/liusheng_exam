package com.liusheng.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.entities.Interlocution;
import com.liusheng.service.InterlocutionService;

@Controller
public class InterAction {
	@Autowired
	private InterlocutionService service;
	@Autowired
	private ServletContext context;
	private Logger log = Logger.getLogger(InterAction.class);

	@ResponseBody
	@RequestMapping(value = "/getpagesinter/{page}/{itemNums}/{state}")
	public List<Interlocution> getPagesInterlocation(@PathVariable int page,
			@PathVariable int itemNums,@PathVariable int state) {
		log.info("查询pages问答题: start:" + page + ",itemNums:" + itemNums);
		return service.getAllInterlocution(page, itemNums,state);
	}

	@ResponseBody
	@RequestMapping(value = "/getinterpages")
	public int getInterLocationPageNums(int items){
		return service.geteInterLocutionPageNums(items);
	}
	
	@ResponseBody
	@RequestMapping("/addinter")
	public String addInterlocation(Interlocution il, MultipartFile file) {
		boolean b = service.addOneInterlocution(il, file,context);
		JSONObject obj = new JSONObject();
		obj.put("code", b?0:1);
		return obj.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteil/{id}")
	public boolean delteInterLocation(@PathVariable int id){
		return service.deleteOneInterlocution(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/agreeil")
	public boolean checkOneInterlocution(int agreeId,String question,String answer,String keypoint,String keypointId){
		return service.checkOneInterlocution(agreeId, question, answer, keypoint, keypointId);
	}
}
