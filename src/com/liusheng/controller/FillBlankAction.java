package com.liusheng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
import com.liusheng.service.FillBlankService;

@RestController
public class FillBlankAction {

	@Autowired
	private FillBlankService fservice;

	@RequestMapping("/getpagesfb/{page}/{items}/{state}")
	public List<FillBlank> getpagesFillblank(@PathVariable int page,
			@PathVariable int items,@PathVariable int state) {
		return fservice.getAllFillBlank(page, items,state);
	}

	@RequestMapping("/getfbpages")
	public int getfillBlankpageNums(int items){
		return fservice.getFillBlankPageNums(items);
		
	}
	
	@RequestMapping("/getfbans/{fbId}")
	public List<FillBlankAnswer> getFBanswer(@PathVariable int fbId) {
		return fservice.getFBAnswer(fbId);
	}

	@RequestMapping(value="/addfb",produces="text/html;charset=utf-8")
	public String addFillblank(FillBlank fb) {
		String b = fservice.addOneFillBlank(fb);
		return b;
	}
	
	@RequestMapping("/deletefb/{id}")
	public boolean deleteFillBlankById(@PathVariable int id){
		return fservice.deleteOneFillBlank(id);
	}
	
	@RequestMapping("/agreefb")
	public boolean checkOneFillBlank(int agreeId,String question,String keypoint,String keypointId) {
		return fservice.checkOneFillBlank(agreeId,question,keypoint,keypointId);
	}

}
