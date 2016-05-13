package com.swu.shake.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.CollectionDao;
import com.swu.shake.model.Collection;
import com.swu.shake.util.HibernateUtil;

@Repository(value = "collectionDao")
public class CollectionDaoImp implements CollectionDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Autowired
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Override
	public Collection save(Collection collection) {
		Session session = null;
		Transaction transaction = null;
		Collection c = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			c = (Collection) session.load(Collection.class, session.save(collection));
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return c;
	}

	@Override
	public boolean deleteByColid(int colid) {
		String hql = "delete from Collection where colid=" + colid;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean deleteByUid(int uid) {
		String hql = "delete from Collection col where col.user.uid=" + uid;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public List<Collection> findall(int uid) {
		String hql = "from Collection col where col.user.uid=" + uid + "order by col.colid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Collection> getCollections(int uid, int start, int end) {
		String hql = "from Collection col where col.user.uid=" + uid;
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public long getCount(int uid) {
		String hql = "select count(*) from Collection col where col.user.uid=" + uid;
		return this.hibernateUtil.exeCount(hql);
	}

	@Override
	public Collection isMyCol(int uid, int iid) {
		Collection coll = null;
		String hql = "from Collection coll where coll.user.uid=" + uid + " and coll.item.iid=" + iid;
		try {
			List<Collection> list = hibernateUtil.exeQuery(hql);
			if (list.size() != 0) {
				coll = (Collection) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}

	@Override
	public boolean deleteByIid(int iid) {
		String hql = "delete from Collection col where col.item.iid=" + iid;
		return hibernateUtil.exeDelete(hql);
	}

}
