package com.liusheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.SimpleSelection;

@Service
public class SimpleSelectService {

	@Autowired
	private SimpleSelectDao ssDao;
	
	public void addOneSimpleSelection(SimpleSelection ss) {
		ssDao.addOneSimpleSelection(ss);
	}

	public boolean deleteOneSimpleSelection(int id) {
		return ssDao.deleteOneSimpleSelection(id);
	}

	public void updataOneSimpleSelection(SimpleSelection ss) {
	}

	public SimpleSelection getOneSimpleSelection(int id) {
		return null;
	}

	public List<SimpleSelection> getAllSimpleSelection(int start, int itemNums) {
		return ssDao.getAllSimpleSelection(start, itemNums);
	}

	public boolean checkOneSimpleSelection(int id) {
		return ssDao.checkOneSimpleSelection(id);
	}
}
