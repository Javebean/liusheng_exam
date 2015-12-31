package com.liusheng.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.KeypointsDao;
import com.liusheng.entities.Keypoints;

@Repository
@Transactional
public class KeypointsDaoImpl implements KeypointsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addKeypoints(Keypoints kp) {
		try {
			getSession().save(kp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public int deleteKeypoints(int id) {
		try {
			String hql = "delete from Keypoints where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}

	@Override
	public boolean updateKeypoints(Keypoints kp) {
		try {
			getSession().update(kp);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Keypoints getOneKeypoints(int id) {
		try {
			String hql = "from Keypoints where id = ?";
			Keypoints kp = (Keypoints) getSession().createQuery(hql)
					.setInteger(0, id).uniqueResult();
			return kp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Keypoints> getAllKeypoints(int start, int items) {
		try {
			String hql = "from Keypoints";
			List<Keypoints> kp = getSession().createQuery(hql)
					.setFirstResult(start).setMaxResults(items).list();
			return kp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
