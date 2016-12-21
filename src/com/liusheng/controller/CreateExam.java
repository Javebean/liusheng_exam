package com.liusheng.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.service.CreateExamService;

@RestController
public class CreateExam {

	@Autowired
	private CreateExamService cservice;
	/**
	 * 1.知识点id放进数组 2.随机再对数组进行排序
	 * 
	 * 2016-12-20 对知识点（汉字）数组进行随机排序
	 */
	@Autowired
	private ServletContext context;

	@RequestMapping("/cratexam")
	public String createexam(String simple, String fill, String inter) {
		return cservice.createexamService(simple, fill, inter, context);
	}

}
