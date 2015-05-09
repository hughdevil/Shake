package com.swu.shake.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.swu.shake.dao.UserDao;
import com.swu.shake.model.User;
import com.swu.shake.util.HibernateUtil;

@Component(value = "userDao")
public class UserDaoImpl implements UserDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	// 注入hibernateUtil工具类
	@Resource(name = "hibernateUtil")
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public User save(User user) {
		// TODO Auto-generated method stub
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			u = (User) session.load(User.class, session.save(user));
			transaction.commit();
			hibernateUtil.closeSession(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return u;
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from User where id=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub

		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			User u = (User) session.load(User.class, user.getUid());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setAddr(user.getAddr());
			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			u.setQQ(user.getQQ());

			transaction.commit();

			flag = true;
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();

		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

	public List<User> findall() {
		// TODO Auto-generated method stub
		String hql = "from User";
		return hibernateUtil.exeQuery(hql);
	}

	public User login(String name, String password) {
		// TODO Auto-generated method stub
		User u = null;
		String hql = "from User where name='" + name + "' and password='"
				+ password + "'";
		try {
			List list = hibernateUtil.exeQuery(hql);
			if (list.size() != 0) {
				u = (User) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return u;
	}

	public boolean checkUserName(String username) {
		String hql = "from User where name='" + username + "'";
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return true;
		}
		return false;
	}

	public List<User> getPersons(int start, int end) {
		// TODO Auto-generated method stub
		String hql = "from User";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

}
