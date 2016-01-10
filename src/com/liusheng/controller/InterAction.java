package com.liusheng.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liusheng.entities.Interlocution;
import com.liusheng.service.InterlocutionService;
import com.liusheng.util.NumberUtil;

@Controller
public class InterAction {
	@Autowired
	private InterlocutionService service;

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
	
	@RequestMapping("/addinter")
	public String addInterlocation(Interlocution il, MultipartFile file,Map<String,String>map) {
		il.setNumber(NumberUtil.createNum());
		boolean b = service.addOneInterlocution(il, file);
		String text = null;
		if (b) {
			text = "上传成功";
		} else {
			text = "上传失败";
		}
		map.put("message", text);
		return "redirect:uploadState.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteil/{id}")
	public boolean delteInterLocation(@PathVariable int id){
		return service.deleteOneInterlocution(id);
	}
}
