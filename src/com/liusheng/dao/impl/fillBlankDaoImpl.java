package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.fillBlankDao;
import com.liusheng.entities.fillBlank;
import com.liusheng.util.constant;

@Repository
@Transactional
public class fillBlankDaoImpl implements fillBlankDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOneFillBlank(fillBlank fb) {
		try {
			getSession().save(fb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteOneFillBlank(int id) {
		try {
			String hql = "delete from fillBlank where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updataOneFillBlank(fillBlank fb) {
		try {
			getSession().update(fb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public fillBlank getOneFillBlank(int id) {
		try {
			String hql = "from fillBlank where id = ?";
			return (fillBlank) getSession().createQuery(hql).setInteger(0, id)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<fillBlank> getAllFillBlank(int start, int itemNums) {
		try {
			String hql = "from fillBlank where id = ?";
			List<fillBlank> list = getSession().createQuery(hql)
					.setFirstResult(start).setMaxResults(itemNums).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean checkOneFillBlank(int id) {
		try {
			String hql = "update fillBlank set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}
}
