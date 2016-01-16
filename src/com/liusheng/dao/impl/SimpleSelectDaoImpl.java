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
	public boolean addOneSimpleSelection(SimpleSelection ss) {
		try {
			getSession().save(ss);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public List<SimpleSelection> getAllSimpleSelection(int start, int itemNums,int state) {
		try {
			String hql = "from SimpleSelection where checkStatus =?";
			List<SimpleSelection> list = getSession().createQuery(hql).setInteger(0, state)
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
			getSession().createQuery(hql).setInteger(0, Constant.CHECK_SUCCESS)
					.setInteger(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*突然发现sql可以直接查询随机多少条数据*/
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleSelection> createSimpleByKid(String kpId, int limit) {
		try {
			String hql = "from SimpleSelection where keypointId =? order by rand()";
			return  getSession().createQuery(hql).setString(0, kpId)
					.setMaxResults(limit)
					.list();
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
			Long count =  (Long) getSession().createQuery(hql).setString(0, kpId)
					.uniqueResult();
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
