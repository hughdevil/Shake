package com.swu.shake.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Hibernate工具类
 * <p>
 * 1.重开session的工具类 
 * <p>
 * 2.查、删的工具类，不负责增、改
 * 
 * @author Shuai
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HibernateUtil {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 重开一个独立的session
	 * 
	 * @return
	 */
	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	/**
	 * session关闭
	 * 
	 * @param session
	 */
	public void closeSession(Session session) {
		try {
			if (null != session && session.isOpen() == true) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 非Spring事务回滚
	 * 
	 * @param transaction
	 */
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
	 * 删除操作 事务回滚处理
	 * <p>
	 * 用sessionFactory.getCurrentSession()来保证session一致
	 * <p>
	 * 仅支持凭借唯一id删除
	 * 
	 * @param hql
	 * @return
	 */
	public boolean exeDelete(String hql) {
		/*
		 * Transaction transaction = null; Session session = null; try { session
		 * = getSession(); session = sessionFactory.getCurrentSession();
		 * transaction = session.beginTransaction();
		 * session.createQuery(hql).executeUpdate(); transaction.commit(); flag
		 * = true; } catch (HibernateException e) { e.printStackTrace(); flag =
		 * false; rollbackTransaction(transaction); } catch (Exception e) {
		 * e.printStackTrace(); } finally { closeSession(session); }
		 */

		boolean flag = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
			throw e;
		}
		return flag;
	}
	
	/**
	 * 通用查询
	 * <p>
	 * SQL非参数化，支持非字符串的HQL语句
	 *  
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
	 * 通用查询
	 * <p>
	 * SQL参数化
	 */
	public List exeQuery(String hql, String... params) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			list = getQuery(session, hql, params).list();
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
	 * 通用分页查询
	 * <p>
	 * SQL非参数化，支持非字符串的HQL语句
	 * 
	 * @param hql
	 * @param start
	 * @param end
	 * @return
	 */
	public List exeQueryPage(String hql, int start, int end) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			// 链式编程
			list = session.createQuery(hql).setFirstResult(start).setMaxResults(end).list();
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
	 * 通用分页查询
	 * <p>
	 * SQL参数化
	 * 
	 * @param hql
	 * @param start
	 * @param end
	 * @return
	 */
	public List exeQueryPage(String hql,int start, int end,String... params) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			// 链式编程
			list = getQuery(session,hql,params).setFirstResult(start).setMaxResults(end).list();
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
	 * 计算总数
	 * <p>
	 * SQL非参数话，支持非字符串的HQL语句
	 * 
	 * @param hql
	 * @return
	 */
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
	
	
	/**
	 * 计算总数
	 * <p>
	 * SQL参数化
	 * 
	 * @param hql
	 * @return
	 */
	public long exeCount(String hql,String... params) {
		Session session = null;
		Transaction transaction = null;
		long count = 0;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			count = (Long) getQuery(session,hql,params).uniqueResult();
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
	
	/**
	 * 处理非SQL参数为字符串的情况
	 * 
	 * @param session
	 * @param hql
	 * @param params
	 * @return
	 */
	private Query getQuery(Session session,String hql,String... params){
		Query query = session.createQuery(hql);
		for (int index = 0; index < params.length; index++) {
			query.setParameter(index, params[index]);
		}
		return query;
	}

}
