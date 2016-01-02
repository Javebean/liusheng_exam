package com.liusheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.service.SimpleSelectService;

@RestController
public class CreateExam {

	@Autowired
	private SimpleSelectService sservice;
	
	@RequestMapping("/cratexam")
	public String createexam(String simple,String fill,String inter){
		sservice.createexamService(simple, fill, inter);
		return "success";
	}
	
}
