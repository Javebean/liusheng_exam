package com.liusheng.dao;

import com.liusheng.entities.Manager;

public interface ManagerDao {
	
	public void addManager(Manager m);
	
	public void deleteManager(int id);
	
	public void updateManager(Manager m);
	
	public Manager getOneManager(String username);
}
