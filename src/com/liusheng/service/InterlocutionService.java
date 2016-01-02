package com.liusheng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.InterlocutionDao;
import com.liusheng.entities.Interlocution;

@Service
public class InterlocutionService {

	@Autowired
	private InterlocutionDao iDao;

	public void addOneInterlocution(Interlocution il) {
		iDao.addOneInterlocution(il);
	}

	public void deleteOneInterlocution(int id) {
	}

	public void updataOneInterlocution(Interlocution ss) {
	}

	public Interlocution getOneInterlocution(int id) {
		return null;
	}

	public List<Interlocution> getAllInterlocution(int start, int itemNums) {
		return iDao.getAllInterlocution(start, itemNums);
	}

	public boolean checkOneInterlocution(int id) {
		return false;
	}

	public List<Interlocution> createInter(Map<String, Integer> map) {
		/**
		 * 1.查询知识点1 kid 的集合 daoImpl里面写的 2.从中随机选择一定数量的题目
		 * 
		 * */
		List<Interlocution> list = new ArrayList<Interlocution>();
		Random random = new Random();
		Interlocution ss = null;
		for (Map.Entry<String, Integer> m : map.entrySet()) {
			String kpId = m.getKey();
			// 该知识点要出的数量
			Integer nums = m.getValue();
			int count = (int) iDao.getInterlocaionCount(kpId);
			for (int i = 0; i < nums; i++) {
				ss = iDao.createInterlocaionByKid(kpId, random.nextInt(count));
				list.add(ss);
			}
		}
		return list;
	}
}
