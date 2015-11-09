package com.liusheng.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.ManagerDao;
import com.liusheng.entities.Manager;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addManager(Manager m) {
		try {
			getSession().save(m);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteManager(int id) {
		try {
			String hql = "delete from Manager where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateManager(Manager m) {
		try {
			getSession().update(m);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public Manager getOneManager(String username) {
		try {
			String hql = "from Manager where username =?";
			Manager manager = (Manager) getSession().createQuery(hql)
					.setString(0, username).uniqueResult();
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
