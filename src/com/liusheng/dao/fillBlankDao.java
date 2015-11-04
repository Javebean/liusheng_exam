package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.fillBlank;

public interface fillBlankDao {

	public void addOneFillBlank(fillBlank fb);

	public void deleteOneFillBlank(int id);

	public void updataOneFillBlank(fillBlank fb);

	public fillBlank getOneFillBlank(int id);

	public List<fillBlank> getAllFillBlank(int start, int itemNums);

	public boolean checkOneFillBlank(int id);
	
}
