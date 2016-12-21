package com.liusheng.dao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			getSession().saveOrUpdate(fb);
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
			String hql = "from FillBlank where checkStatus =? order by id desc";
			List<FillBlank> list = getSession().createQuery(hql).setInteger(0, state)
					.setFirstResult(start).setMaxResults(itemNums).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkOneFillBlank(int agreeId,String question,String keypoint,String keypointId) {
		try {
			Pattern reg = Pattern.compile("(<)(\\W+?)(>)");
			Matcher matcher = reg.matcher(question);
			StringBuilder answer = new StringBuilder();
			while(matcher.find()){
				answer.append(matcher.group(2));
				answer.append(",");
			}
			//删掉最后一个逗号“，”
			answer.deleteCharAt(answer.length()-1);
			
			String hql = "update FillBlank set checkStatus =? ,problem=? ,keypointId=?,keypoint=?,answer=? where id = ?";
			getSession().createQuery(hql).setInteger(0, Constant.CHECK_SUCCESS)
						.setString(1, question).setString(2, keypointId).setString(3, keypoint)
						.setString(4, answer.toString())
					.setInteger(5, agreeId).executeUpdate();
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

	@Override
	public FillBlank createFillBlankByKid(String kpId) {
		try {
			String hql = "from FillBlank where keypointId =? and checkStatus =1 order by rand()";
			return  (FillBlank) getSession().createQuery(hql).setString(0, kpId)
					.setMaxResults(1)
					.uniqueResult();
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
			String hql ="select count(id) from FillBlank  where checkStatus = 1";
			Long count = (Long) getSession().createQuery(hql).uniqueResult();
			return count;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object[] getFillBlankCountByName(String name[]) {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("select");
		for(String n : name){
			sb.append(" sum(case when KEYPOINT = '"+n+"' then 1 else 0 end),");
		}
		sb = sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" from FILL_BLANK where CHECK_STATUS = '"+Constant.CHECK_SUCCESS+"'");
		Object[] res = (Object[]) getSession().createSQLQuery(sb.toString()).uniqueResult();
		if(res[0]==null){
			return null;
		}
		return res;
	}

	@Override
	public FillBlank getRandFillBlankByName(String kpName) {
		try {
			String hql = "from FillBlank where keypoint =? and checkStatus =1 order by rand()";
			return  (FillBlank) getSession().createQuery(hql).setString(0, kpName)
					.setMaxResults(1)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
