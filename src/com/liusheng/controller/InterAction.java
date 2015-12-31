package com.liusheng.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.Interlocution;
import com.liusheng.service.InterlocutionService;

@RestController
public class InterAction {
	@Autowired
	private InterlocutionService service;

	private Logger log = Logger.getLogger(InterAction.class);
	
	@RequestMapping(value = "/getpagesinter/{start}/{itemNums}")
	public List<Interlocution> getPagesInterlocation(@PathVariable int start,
			@PathVariable int itemNums) {
		log.info("查询pages问答题: start:"+ start+",itemNums:"+itemNums);
		return service.getAllInterlocution(start, itemNums);
	}
}
