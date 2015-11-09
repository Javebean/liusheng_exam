package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.InterlocutionDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.util.Constant;
@Repository
@Transactional
public class InterlocutionDaoImpl implements InterlocutionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addOneInterlocution(Interlocution il) {
		try {
			getSession().save(il);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteOneInterlocution(int id) {
		try {
			String hql = "delete from interlocution where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updataOneInterlocution(Interlocution ss) {
		try {
			getSession().update(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public Interlocution getOneInterlocution(int id) {
		String hql = "from interlocution where id = ?";
		return (Interlocution) getSession().createQuery(hql).setInteger(0, id)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interlocution> getAllInterlocution(int start, int itemNums) {
		String hql = "from interlocution where id = ?";
		List<Interlocution> list = getSession().createQuery(hql)
				.setFirstResult(start).setMaxResults(itemNums).list();
		return list;
	}

	@Override
	public boolean checkOneInterlocution(int id) {
		try {
			String hql = "update interlocution set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

}
