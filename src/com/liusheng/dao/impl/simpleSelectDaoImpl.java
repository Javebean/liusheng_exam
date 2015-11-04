package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.simpleSelectDao;
import com.liusheng.entities.simpleSelection;
import com.liusheng.util.constant;
@Repository
@Transactional
public class simpleSelectDaoImpl implements simpleSelectDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addOneSimpleSelection(simpleSelection ss) {
		try {
			getSession().save(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteOneSimpleSelection(int id) {
		try {
			String hql = "delete from simpleSelection where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updataOneSimpleSelection(simpleSelection ss) {
		try {
			getSession().update(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public simpleSelection getOneSimpleSelection(int id) {
		try {
			String hql = "from simpleSelection where id = ?";
			return (simpleSelection) getSession().createQuery(hql).setInteger(0, id)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<simpleSelection> getAllSimpleSelection(int start, int itemNums) {
		try {
			String hql = "from simpleSelection where id = ?";
			List<simpleSelection> list = getSession().createQuery(hql)
					.setFirstResult(start).setMaxResults(itemNums).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean checkOneSimpleSelection(int id) {
		try {
			String hql = "update simpleSelection set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

}
