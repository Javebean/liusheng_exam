package com.liusheng.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liusheng.dao.InterlocutionDao;
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
	public boolean addOneInterlocution(Interlocution il) {
		try {
			getSession().saveOrUpdate(il);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteOneInterlocution(int id) {
		try {
			String hql = "delete from Interlocution where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		Interlocution inter = null;
		try {
			String hql = "from Interlocution where id = ?";
			inter = (Interlocution) getSession().createQuery(hql)
					.setInteger(0, id).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return inter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interlocution> getAllInterlocution(int start, int itemNums,int state) {
		List<Interlocution> list = null;
		try {
			String hql = "from Interlocution where checkStatus =? order by id desc";
			list = getSession().createQuery(hql).setInteger(0, state).setFirstResult(start)
					.setMaxResults(itemNums).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean checkOneInterlocution(int agreeId,String question,String answer,String keypoint,String keypointId) {
		try {
			String hql = "update Interlocution set checkStatus =?,problem=?,answer=?,keypointId=?,keypoint=? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECK_SUCCESS)
					.setString(1, question).setString(2, answer).setString(3, keypointId)
						.setString(4, keypoint)
					.setInteger(5, agreeId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public Interlocution createInterlocaionByKid(String kpId) {
		try {
			String hql = "from Interlocution where keypointId = ? and checkStatus=1 order by rand()";
			return (Interlocution) getSession().createQuery(hql)
					.setString(0, kpId).setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getInterlocaionCount() {
		try {
			String hql = "select count(id) from Interlocution where checkStatus = 1";
			Long count = (Long) getSession().createQuery(hql).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getInterlocaionCount(String kpId) {
		try {
			String hql = "select count(id) from Interlocution where keypointId =?";
			Long count = (Long) getSession().createQuery(hql)
					.setString(0, kpId).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> getInterlocaionCountByName(String name[]) {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("select");
		for(String n : name){
			sb.append(" sum(case when KEYPOINT = '"+n+"' then 1 else 0 end),");
		}
		sb = sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" from INTERLOCUTION where CHECK_STATUS = "+Constant.CHECK_SUCCESS);
		List<BigDecimal> res = getSession().createSQLQuery(sb.toString()).list();
		return res;
	}

	@Override
	public Interlocution getRandInterByName(String kpName) {
		try {
			String hql = "from Interlocution where keypoint =? and checkStatus =1 order by rand()";
			return  (Interlocution) getSession().createQuery(hql).setString(0, kpName)
					.setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
