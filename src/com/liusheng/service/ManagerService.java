package com.liusheng.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.ManagerDao;
import com.liusheng.entities.Manager;
@Service
public class ManagerService {
	final static Logger logger = Logger.getLogger(ManagerService.class);
	
	@Autowired
	private ManagerDao managerDao;
	
	public void addManager(Manager m) {
	}

	public void deleteManager(int id) {
	}

	public void updateManager(Manager m) {
	}

	public Manager getOneManager(String username) {
		logger.info("username: "+username);
		Manager ma = managerDao.getOneManager(username);
		if(ma!=null){
			logger.info("username exist....");
		}else{
			logger.info("username not exist....");
		}
		return ma;
	}
}
