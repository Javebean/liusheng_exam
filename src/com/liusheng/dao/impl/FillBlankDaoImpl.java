package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
import com.liusheng.util.Constant;

@Repository
@Transactional
public class FillBlankDaoImpl implements FillBlankDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOneFillBlank(FillBlank fb) {
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
	public void updataOneFillBlank(FillBlank fb) {
		try {
			getSession().update(fb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public FillBlank getOneFillBlank(int id) {
		try {
			String hql = "from fillBlank where id = ?";
			return (FillBlank) getSession().createQuery(hql).setInteger(0, id)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FillBlank> getAllFillBlank(int start, int itemNums) {
		try {
			String hql = "from FillBlank";
			List<FillBlank> list = getSession().createQuery(hql)
					.setFirstResult(start).setMaxResults(itemNums).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkOneFillBlank(int id) {
		try {
			String hql = "update fillBlank set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FillBlankAnswer> getFBAnswer(int fbId) {
		try {
			String hql = "from FillBlankAnswer where fillBlackId = ?";
			List<FillBlankAnswer> list = getSession().createQuery(hql)
					.setInteger(0, fbId).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
