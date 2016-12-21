package com.liusheng.dao;

import java.util.List;

import com.liusheng.entities.SimpleSelection;

public interface SimpleSelectDao {
	public boolean addOneSimpleSelection(SimpleSelection ss);
	
	public boolean deleteOneSimpleSelection(int id);
	
	public void updataOneSimpleSelection(SimpleSelection ss);
	
	public SimpleSelection getOneSimpleSelection(int id);
	/*state 审核状态*/
	public List<SimpleSelection> getAllSimpleSelection(int start,int itemNums,int state);
	
	public boolean checkOneSimpleSelection(int agreeId,String question,String option,String keypoint,String optionSy,String keypointId);
	
	public SimpleSelection createSimpleByKid(String kpId);
	
	public SimpleSelection getRandSimpleByName(String kpName);
	
	public long getSimpleSelectionCount();
	
	//查询属于该知识点的单选题的数量
	public long getSimpleSelectionCount(String kpId );
	
	public Object[] getSimpleSelectionCountByName(String name[]);
	
}
