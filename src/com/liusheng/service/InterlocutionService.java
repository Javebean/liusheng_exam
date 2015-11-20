package com.liusheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.InterlocutionDao;
import com.liusheng.entities.Interlocution;
@Service
public class InterlocutionService {

	@Autowired
	private InterlocutionDao iDao;
	public void addOneInterlocution(Interlocution il) {
		iDao.addOneInterlocution(il);
	}

	public void deleteOneInterlocution(int id) {
	}

	public void updataOneInterlocution(Interlocution ss) {
	}

	public Interlocution getOneInterlocution(int id) {
		return null;
	}

	public List<Interlocution> getAllInterlocution(int start, int itemNums) {
		return null;
	}

	public boolean checkOneInterlocution(int id) {
		return false;
	}
}
