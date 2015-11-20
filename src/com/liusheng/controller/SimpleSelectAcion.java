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

	@RequestMapping("/getpagess/{start}/{itemNums}")
	public List<SimpleSelection> getPageSimpleSelestartct( @PathVariable int start,
			@PathVariable int itemNums) {
		log.info("参数--start:"+start+"--itemNums: "+itemNums);
		List<SimpleSelection> results = service.getAllSimpleSelection(start,
				itemNums);
		log.info("选择题的结果："+results);
		return results;
	}

}
