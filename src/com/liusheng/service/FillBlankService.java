package com.liusheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.entities.FillBlank;
@Service
public class FillBlankService {
	@Autowired
	private FillBlankDao fillDao;
	public void addOneFillBlank(FillBlank fb) {
		fillDao.addOneFillBlank(fb);
	}

	public void deleteOneFillBlank(int id) {
	}

	public void updataOneFillBlank(FillBlank fb) {
	}

	public FillBlank getOneFillBlank(int id) {
		return null;
	}

	public List<FillBlank> getAllFillBlank(int start, int itemNums) {
		return null;
	}

	public boolean checkOneFillBlank(int id) {
		return false;
	}
}
