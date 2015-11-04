package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.interlocution;


public interface interlocutionDao {

	public void addOneInterlocution(interlocution il);

	public void deleteOneInterlocution(int id);

	public void updataOneInterlocution(interlocution ss);

	public interlocution getOneInterlocution(int id);

	public List<interlocution> getAllInterlocution(int start, int itemNums);

	public boolean checkOneInterlocution(int id);
	
}
