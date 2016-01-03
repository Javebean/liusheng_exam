package com.liusheng.controller;

import java.util.List;

import org.apache.log4j.Logger;
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

	private Logger log = Logger.getLogger(InterAction.class);

	@ResponseBody
	@RequestMapping(value = "/getpagesinter/{start}/{itemNums}")
	public List<Interlocution> getPagesInterlocation(@PathVariable int start,
			@PathVariable int itemNums) {
		log.info("查询pages问答题: start:" + start + ",itemNums:" + itemNums);
		return service.getAllInterlocution(start, itemNums);
	}

	@RequestMapping("/addinter")
	public String addInterlocation(Interlocution il, MultipartFile file) {
		boolean b = service.addOneInterlocution(il, file);
		if (b) {
			return "upload3";
		} else {
			return "error";
		}
	}
}
