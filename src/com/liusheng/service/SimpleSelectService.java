package com.liusheng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.AnalyzeExcel;

@Service
public class SimpleSelectService {

	@Autowired
	private SimpleSelectDao ssDao;
	
	public void addOneSimpleSelection(SimpleSelection ss) {
		List<List<String>> excel = AnalyzeExcel.analyzeExcel(0);
		for(List<String> rowData: excel){
			ss.setProblem(rowData.get(0));
			ss.setOptionA(rowData.get(1));
			ss.setOptionB(rowData.get(2));
			ss.setOptionC(rowData.get(3));
			ss.setOptionD(rowData.get(4));
			
			ssDao.addOneSimpleSelection(ss);
		}
		
	}

	public void deleteOneSimpleSelection(int id) {
	}

	public void updataOneSimpleSelection(SimpleSelection ss) {
	}

	public SimpleSelection getOneSimpleSelection(int id) {
		return null;
	}

	public List<SimpleSelection> getAllSimpleSelection(int start, int itemNums) {
		return null;
	}

	public boolean checkOneSimpleSelection(int id) {
		return false;
	}
}
