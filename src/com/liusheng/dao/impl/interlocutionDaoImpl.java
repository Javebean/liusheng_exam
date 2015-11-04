package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.interlocutionDao;
import com.liusheng.entities.fillBlank;
import com.liusheng.entities.interlocution;
import com.liusheng.util.constant;
@Repository
@Transactional
public class interlocutionDaoImpl implements interlocutionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addOneInterlocution(interlocution il) {
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
	public void updataOneInterlocution(interlocution ss) {
		try {
			getSession().update(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public interlocution getOneInterlocution(int id) {
		String hql = "from interlocution where id = ?";
		return (interlocution) getSession().createQuery(hql).setInteger(0, id)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<interlocution> getAllInterlocution(int start, int itemNums) {
		String hql = "from interlocution where id = ?";
		List<interlocution> list = getSession().createQuery(hql)
				.setFirstResult(start).setMaxResults(itemNums).list();
		return list;
	}

	@Override
	public boolean checkOneInterlocution(int id) {
		try {
			String hql = "update interlocution set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

}
