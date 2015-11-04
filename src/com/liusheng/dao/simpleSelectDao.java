package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.simpleSelection;

public interface simpleSelectDao {
	public  void addOneSimpleSelection(simpleSelection ss);
	
	public void deleteOneSimpleSelection(int id);
	
	public void updataOneSimpleSelection(simpleSelection ss);
	
	public simpleSelection getOneSimpleSelection(int id);
	
	public List<simpleSelection> getAllSimpleSelection(int start,int itemNums);
	
	public boolean checkOneSimpleSelection(int id);
}
