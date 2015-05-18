package com.swu.shake.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.RoleDao;
import com.swu.shake.model.Role;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Role r = null;
		try {
			// session不需要关闭，到时候事务提交后会自动关闭
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			r = (Role) session.load(Role.class, session.save(role));
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		// finally {
		// session.close();
		// }
		return r;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Role role = getRoleById(id);
			session.delete(role);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

	@Override
	public boolean update(Role role) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			// 特殊注意role
			session.update(role);
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			return flag;
		}
	}

	@Override
	public Role getRoleById(int id) {
		Session session = null;
		Transaction transaction = null;
		Role role = null;
		try {
			session = sessionFactory.getCurrentSession();
			if (null == session) {
				session = sessionFactory.openSession();
			}
			transaction = session.beginTransaction();
			// load好不到时返回错误，这与save的不一样
			role = (Role) session.load(Role.class, id);
		} catch (ObjectNotFoundException e) {
			return null;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return role;
	}

	@Override
	public List<Role> findall() {
		// TODO Auto-generated method stub
		// 试试可以用-1来表示无限，如果不可以在对-1做特殊判断
		return findallByPage(-1, -1);
	}

	@Override
	public List<Role> findallByPage(int start, int end) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<Role> list = null;
		boolean isNeedClose = false;
		try {
			session = sessionFactory.getCurrentSession();
			if (null == session) {
				session = sessionFactory.openSession();
				isNeedClose = true;
			}
			transaction = session.beginTransaction();
			String sql = "select * from t_role";
			Query q = session.createSQLQuery(sql);
			list = q.setFirstResult(start).setMaxResults(end).list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (isNeedClose)
				session.close();
		}
		return list;
	}

	@Override
	public List<Role> getRolesByName(String str) {
		// TODO Auto-generated method stub
		// 还是-1的问题
		return getRolesByName(str, -1, -1);
	}

	@Override
	public List<Role> getRolesByName(String str, int start, int end) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<Role> list = null;
		boolean isNeed = false;
		try {
			session = sessionFactory.getCurrentSession();
			if (null == session) {
				session = sessionFactory.openSession();
				isNeed = true;
			}
			transaction = session.beginTransaction();
			Criteria c = session.createCriteria(Role.class);
			c.setFirstResult(start).setMaxResults(end)
					.add(Restrictions.like("rname", "%str%"));
			list = c.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (isNeed)
				session.close();
		}
		return list;
	}
}
