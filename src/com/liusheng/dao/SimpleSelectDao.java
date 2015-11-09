package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.SimpleSelection;

public interface SimpleSelectDao {
	public  void addOneSimpleSelection(SimpleSelection ss);
	
	public void deleteOneSimpleSelection(int id);
	
	public void updataOneSimpleSelection(SimpleSelection ss);
	
	public SimpleSelection getOneSimpleSelection(int id);
	
	public List<SimpleSelection> getAllSimpleSelection(int start,int itemNums);
	
	public boolean checkOneSimpleSelection(int id);
}
