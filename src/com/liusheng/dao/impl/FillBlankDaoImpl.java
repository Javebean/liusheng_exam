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
	public boolean addOneFillBlank(FillBlank fb) {
		try {
			getSession().save(fb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteOneFillBlank(int id) {
		try {
			String hql = "delete from FillBlank where id = ?";
			getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
			String hql = "from FillBlank where id = ?";
			return (FillBlank) getSession().createQuery(hql).setInteger(0, id)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FillBlank> getAllFillBlank(int start, int itemNums,int state) {
		try {
			String hql = "from FillBlank where checkStatus =?";
			List<FillBlank> list = getSession().createQuery(hql).setInteger(0, state)
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
			String hql = "update FillBlank set checkStatus =? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECK_SUCCESS)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<FillBlank> createFillBlankByKid(String kpId[]) {
		try {
			int length = kpId.length;
			if(length>0){
				StringBuilder hql = new StringBuilder("from FillBlank where keypointId in (");
				int i=0;
				for(;i<length;i++){
					hql.append(kpId[i]);
					if(i!=length-1){
						hql.append(",");
					}
				}
				hql.append(") order by rand()");
				System.out.println("填空题的sql "+hql.toString());
				//先取10题。然后再service层，再从中取10个空
				return (List<FillBlank>) getSession().createQuery(hql.toString()).setMaxResults(10).list();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getFillBlankCount(String kpId) {
		try {
			String hql = "select count(id) from FillBlank where keypointId =?";
			Long count = (Long) getSession().createQuery(hql)
					.setString(0, kpId).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getFillBlankCount() {
		try{
			String hql ="select count(id) from FillBlank";
			Long count = (Long) getSession().createQuery(hql).uniqueResult();
			return count;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
