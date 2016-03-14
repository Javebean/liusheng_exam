package com.liusheng.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.service.SimpleSelectService;

@RestController
public class CreateExam {

	@Autowired
	private SimpleSelectService sservice;
	/**
	 * 1.知识点id放进数组
	 * 2.随机再对数组进行排序
	 * 
	 */
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/cratexam")
	public String createexam(String simple,String fill,String inter){
		sservice.createexamService(simple, fill, inter,context);
		return "success";
	}
	
}
