package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.FillBlank;

public interface FillBlankDao {

	public void addOneFillBlank(FillBlank fb);

	public void deleteOneFillBlank(int id);

	public void updataOneFillBlank(FillBlank fb);

	public FillBlank getOneFillBlank(int id);

	public List<FillBlank> getAllFillBlank(int start, int itemNums);

	public boolean checkOneFillBlank(int id);
	
}
