package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Interlocution;


public interface InterlocutionDao {

	public void addOneInterlocution(Interlocution il);

	public void deleteOneInterlocution(int id);

	public void updataOneInterlocution(Interlocution ss);

	public Interlocution getOneInterlocution(int id);

	public List<Interlocution> getAllInterlocution(int start, int itemNums);

	public boolean checkOneInterlocution(int id);
	
}
