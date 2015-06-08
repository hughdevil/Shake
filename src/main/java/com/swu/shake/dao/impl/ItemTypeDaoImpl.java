package com.swu.shake.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.ItemTypeDao;
import com.swu.shake.model.ItemType;

@Repository(value = "itemTypeDao")
public class ItemTypeDaoImpl implements ItemTypeDao {
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ItemType save(ItemType itemType) {
		// TODO Auto-generated method stub
		ItemType it = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			it = (ItemType) session
					.load(ItemType.class, session.save(itemType));
			session.getTransaction().commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return it;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(getItemTypeById(id));
			session.getTransaction().commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean update(ItemType itemType) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ItemType it = getItemTypeById(itemType.getTid());
			it.setTname(itemType.getTname());

			session.update(it);
			session.getTransaction().commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public List<ItemType> findall() {
		Session session = null;
		Transaction transaction = null;
		List<ItemType> its = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(ItemType.class);
			its = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return its;
	}

	@Override
	public List<ItemType> getItemTypesByName(String str, int start, int end) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<ItemType> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(ItemType.class)
					.setFirstResult(start).setMaxResults(end)
					.add(Restrictions.like("tname", "%" + str + "%"));
			list = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public ItemType getItemTypeById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ItemType itemType = (ItemType) session.get(ItemType.class, id);
		session.getTransaction().commit();
		session.close();
		return itemType;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<ItemType> its = null;
		int sum = 0;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "select count(*) from ItemType";
			sum = (Integer) session.createQuery(hql).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sum;
	}

}
