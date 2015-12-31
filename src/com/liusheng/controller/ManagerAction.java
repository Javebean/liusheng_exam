package com.liusheng.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liusheng.entities.CommonResult;
import com.liusheng.entities.Manager;
import com.liusheng.entities.Result;
import com.liusheng.service.ManagerService;
import com.liusheng.util.MD5;

@RestController
public class ManagerAction {
	final static Logger logger = Logger.getLogger(ManagerAction.class);

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public CommonResult login( String username, String password) {
		logger.info("用户名："+username+" 密码："+password);
		Manager ma = managerService.getOneManager(username);
		CommonResult cr = null;
		if (ma != null) {
			if (ma.getPassword().equals(MD5.compute(password))) {
				logger.info("login success!....");
				cr = new CommonResult(new Result(0, "登陆成功！"), ma);
				logger.info(cr);
			} else {
				logger.info("password error!!");
				cr = new CommonResult(new Result(1, "密码不正确！"), ma);
			}
		}else{
			cr = new CommonResult(new Result(1, "没有该用户！"), ma);
		}
		return cr;
	}
}
