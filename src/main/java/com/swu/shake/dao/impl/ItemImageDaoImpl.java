package com.swu.shake.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.ItemImageDao;
import com.swu.shake.model.ItemImage;
import com.swu.shake.util.HibernateUtil;

@Repository(value = "itemImageDao")
public class ItemImageDaoImpl implements ItemImageDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Autowired
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Override
	public List<ItemImage> findall(int iid) {
		List<ItemImage> list = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String sql = "select * from t_itemimage where iid=" + iid;
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(ItemImage.class);
			list = sqlQuery.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} finally {
			hibernateUtil.closeSession(session);
		}
		return list;
	}

	@Override
	public ItemImage save(ItemImage itemImage) {
		ItemImage ii = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			ii = (ItemImage) session.load(ItemImage.class,
					session.save(itemImage));

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} finally {
			hibernateUtil.closeSession(session);
		}
		return ii;
	}

	@Override
	public boolean delete() {
		Session session = null;
		Transaction transaction = null;
		boolean flag = true;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String sql = "delete from t_itemimage where iid is " + null;
			session.createSQLQuery(sql).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			hibernateUtil.rollbackTransaction(transaction);
			flag = false;
		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@Override
	public boolean delete(int iiid) {
		String hql = "delete from ItemImage where iiid = " + iiid;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean deleteByIid(int iid) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = true;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String sql = "delete from t_itemimage where iid=" + iid;
			session.createSQLQuery(sql).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			hibernateUtil.rollbackTransaction(transaction);
			flag = false;
		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

}
