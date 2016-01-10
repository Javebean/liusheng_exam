package com.liusheng.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.SimpleSelection;
import com.liusheng.service.SimpleSelectService;

@RestController
public class SimpleSelectAcion {

	Logger log = Logger.getLogger(SimpleSelectAcion.class);
	@Autowired
	private SimpleSelectService service;

	@RequestMapping("/getpagess/{page}/{itemNums}/{state}")
	public List<SimpleSelection> getPageSimpleSelestartct( @PathVariable int page,
			@PathVariable int itemNums,@PathVariable int state) {
		log.info("参数--page:"+page+"--itemNums: "+itemNums);
		List<SimpleSelection> results = service.getAllSimpleSelection(page,
				itemNums,state);
		log.info("选择题的结果："+results);
		return results;
	}
	
	@RequestMapping("/getsimpages")
	public int getsimpleselectPageNums(int items){
		return service.getsimplecount(items);
	}
	
	
	@RequestMapping("/agreeques/{id}")
	public boolean agreeQues(@PathVariable int id){
		log.info("审核通过题目的id "+id);
		return service.checkOneSimpleSelection(id);
	}
	
	@RequestMapping("/deletesim/{id}")
	public boolean deleteSim(@PathVariable int id){
		return service.deleteOneSimpleSelection(id);
	}
	
 	@RequestMapping("/addsimpleselect")
	public String addSimpleSelect(SimpleSelection ss){
 		System.out.println(ss);
		boolean addOneSimpleSelection = service.addOneSimpleSelection(ss);
		System.out.println(addOneSimpleSelection);
		return "success";
	}

}
