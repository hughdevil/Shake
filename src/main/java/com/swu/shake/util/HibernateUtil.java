package com.swu.shake.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HibernateUtil {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 注入sessionFactory，下面的方法根据自己的需求，建造工具类
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public void closeSession(Session session) {
		try {
			if (null != session && session.isOpen() == true) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rollbackTransaction(Transaction transaction) {
		try {
			if (null != transaction) {
				transaction.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 方法返回一个List集合的统一查询方法
	 */
	public List exeQuery(String hql) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			list = session.createQuery(hql).list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return list;

	}

	/**
	 * 分页查询方法 方法返回一个List集合
	 */
	public List exeQueryPage(String hql, int start, int end) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			list = session.createQuery(hql).setFirstResult(start)
					.setMaxResults(end).list();
			// Query query=session.createQuery(hql);
			// query.setFetchSize(start);
			// query.setMaxResults(end);
			// list.=query.list();
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return list;

	}

	public boolean exeDelete(String hql) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.createQuery(hql).executeUpdate();
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return flag;

	}

	public long exeCount(String hql) {
		Session session = null;
		Transaction transaction = null;
		long count = 0;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			count = (Long) session.createQuery(hql).uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return count;
	}

}
