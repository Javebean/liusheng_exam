package com.liusheng.dao.impl;

import java.math.BigDecimal;
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
			getSession().saveOrUpdate(ss);
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
			String hql = "from SimpleSelection where checkStatus =? order by id desc";
			List<SimpleSelection> list = getSession().createQuery(hql).setInteger(0, state)
					.setFirstResult(start).setMaxResults(itemNums).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean checkOneSimpleSelection(int agreeId,String question,String option,String keypoint,String optionSy,String keypointId) {
		try {
			String hql = "update SimpleSelection set checkStatus =?,problem =?,answerText=?,answer=?,keypointId=?,keypoint=? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECK_SUCCESS).setString(1, question).setString(2, option).setString(3, optionSy)
					.setString(4, keypointId).setString(5, keypoint)
					.setInteger(6, agreeId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*突然发现sql可以直接查询随机多少条数据*/
	@Override
	public SimpleSelection createSimpleByKid(String kpId) {
		try {
			String hql = "from SimpleSelection where keypointId =? and checkStatus =1 order by rand()";
			return  (SimpleSelection) getSession().createQuery(hql).setString(0, kpId)
					.setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getSimpleSelectionCount() {
		try {
			String hql = "select count(id) from SimpleSelection where checkStatus = 1";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> getSimpleSelectionCountByName(String name[]) {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("select");
		for(String n : name){
			sb.append(" sum(case when KEYPOINT = '"+n+"' then 1 else 0 end),");
		}
		sb = sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" from SIMPLE_SELECTION where CHECK_STATUS = "+Constant.CHECK_SUCCESS);
		//BigDecimal[] res = (BigDecimal[]) getSession().createSQLQuery(sb.toString()).uniqueResult();
		List<BigDecimal> res = getSession().createSQLQuery(sb.toString()).list();
		return res;
	}

	@Override
	public SimpleSelection getRandSimpleByName(String kpName) {
		try {
			String hql = "from SimpleSelection where keypoint =? and checkStatus =1 order by rand()";
			return  (SimpleSelection) getSession().createQuery(hql).setString(0, kpName)
					.setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
