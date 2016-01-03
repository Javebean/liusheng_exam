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

	@RequestMapping("/getpagesfb/{start}/{items}")
	public List<FillBlank> getpagesFillblank(@PathVariable int start,
			@PathVariable int items) {
		return fservice.getAllFillBlank(start, items);
	}

	@RequestMapping("/getfbans/{fbId}")
	public List<FillBlankAnswer> getFBanswer(@PathVariable int fbId) {
		return fservice.getFBAnswer(fbId);
	}

	@RequestMapping("/addfb")
	public boolean addFillblank(FillBlank fb) {
		boolean b = fservice.addOneFillBlank(fb);
		System.out.println(b);
		return b;
	}

}
