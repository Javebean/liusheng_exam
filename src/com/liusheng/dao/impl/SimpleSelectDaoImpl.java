package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.Constant;

@Repository
@Transactional
public class SimpleSelectDaoImpl implements SimpleSelectDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOneSimpleSelection(SimpleSelection ss) {
		try {
			getSession().save(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean deleteOneSimpleSelection(int id) {
		try {
			String hql = "delete from SimpleSelection where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void updataOneSimpleSelection(SimpleSelection ss) {
		try {
			getSession().update(ss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public SimpleSelection getOneSimpleSelection(int id) {
		try {
			String hql = "from SimpleSelection where id = ?";
			return (SimpleSelection) getSession().createQuery(hql)
					.setInteger(0, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleSelection> getAllSimpleSelection(int start, int itemNums) {
		try {
			String hql = "from SimpleSelection";
			List<SimpleSelection> list = getSession().createQuery(hql)
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
			String hql = "update SimpleSelection set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECKSUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public SimpleSelection createSimpleByKid(String kpId, int romdom) {
		try {
			String hql = "from SimpleSelection where keypointId =?";
			return (SimpleSelection) getSession().createQuery(hql)
					.setString(0, kpId).setFirstResult(romdom).setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getSimpleSelectionCount() {
		try {
			String hql = "select count(id) from SimpleSelection";
			Long count = (Long) getSession().createQuery(hql).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getSimpleSelectionCount(String kpId) {
		try {
			String hql = "select count(id) from SimpleSelection where keypointId =?";
			return (Long) getSession().createQuery(hql).setString(0, kpId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
