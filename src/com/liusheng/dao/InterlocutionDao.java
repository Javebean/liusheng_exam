package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.Interlocution;


public interface InterlocutionDao {

	public boolean addOneInterlocution(Interlocution il);

	public boolean deleteOneInterlocution(int id);

	public void updataOneInterlocution(Interlocution ss);

	public Interlocution getOneInterlocution(int id);

	public List<Interlocution> getAllInterlocution(int start, int itemNums);

	public boolean checkOneInterlocution(int id);
	
	public Interlocution createInterlocaionByKid(String kpId , int romdom);
	
	public long getInterlocaionCount();
	
	//查询属于该知识点的单选题的数量
	public long getInterlocaionCount(String kpId );
	
}
