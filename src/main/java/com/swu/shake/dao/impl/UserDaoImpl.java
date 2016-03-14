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
import com.swu.shake.util.MsgException;

/**
 * sqlinject
 */
@Component(value = "userDao")
public class UserDaoImpl implements UserDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Resource(name = "hibernateUtil")
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public User save(User user) {
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			u = (User) session.load(User.class, session.save(user));
			transaction.commit();
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

	public boolean delete(int uid) {
		String hql = "delete from User where uid=" + uid;
		return hibernateUtil.exeDelete(hql);
	}

	public boolean update(User user) {

		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			User u = (User) session.load(User.class, user.getUid());

			// 修改资料必备的8项
			if (u.getName().equals(user.getName())) {
				u.setName(user.getName());
			} else if (checkUserName(user.getName())) {
				throw new MsgException("用户名重复");
			}
			u.setSex(user.getSex());

			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			u.setQQ(user.getQQ());
			u.setAddr(user.getAddr());

			u.setIP(user.getIP());
			if (user.getHeadpic() != null)
				u.setHeadpic(user.getHeadpic());

			// 他人修改自己的Role，通过实参是否为null判断l
			if (user.getRole() != null)
				u.setRole(user.getRole());
			flag = true;

			transaction.commit();

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

	public boolean updatePwd(User user) {

		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			User u = (User) session.load(User.class, user.getUid());
			// 自己修改密码
			u.setPassword(user.getPassword());
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

	@Override
	public boolean removeRole(String rid) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String sql = "update t_user set  rid=null where rid=:rid";
			session.createSQLQuery(sql).setString("rid", rid).executeUpdate();
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
		String hql = "from User";
		return hibernateUtil.exeQuery(hql);
	}

	public List<User> getUsersByPage(int start, int end) {
		String hql = "from User";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	public List<User> getUsersByPage(String username, int start, int end) {
		String hql = "from User where name like ?";
		return hibernateUtil.exeQueryPage(hql, start, end, "%" + username + "%");
	}

	public User login(String name, String password) {
		User u = null;
		String hql = "from User where name=? and password=?";
		try {
			List<User> list = hibernateUtil.exeQuery(hql, name, password);
			if (list.size() != 0) {
				u = (User) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public List<User> getUsersByName(String username) {
		String hql = "from User where name like ?";
		return hibernateUtil.exeQuery(hql, "%" + username + "%");
	}

	@Override
	public User getUserById(int uid) {
		String hql = "from User u where u.uid=" + uid;
		List<User> users = hibernateUtil.exeQuery(hql);
		User user = null;
		if (users != null && !users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public boolean checkUserName(String username) {
		String hql = "from User where name like ?";
		List<User> users = hibernateUtil.exeQuery(hql,"%"+ username + "%");

		if (users != null && !users.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> getUsersByNameNoLike(String username) {
		String hql = "from User where name = ?";
		List<User> users = hibernateUtil.exeQuery(hql, username);
		return users;
	}
}
