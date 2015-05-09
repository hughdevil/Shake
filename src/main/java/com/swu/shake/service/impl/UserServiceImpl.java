package com.swu.shake.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.swu.shake.dao.UserDao;
import com.swu.shake.model.User;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MsgException;

@Component(value = "userService")
public class UserServiceImpl implements UserService {
	UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	// 注入userDao层
	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User register(User user) throws MsgException {
		// TODO Auto-generated method stub
		User u = null;
		if (!checkUserName(user.getName())) {
			u = userDao.save(user);
		} else {
			throw new MsgException("用户名已经存在");
		}
		return u;
	}

	public boolean remove(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			return userDao.delete(Integer.parseInt(ids[i]));
		}
		return flag;

	}

	public boolean modify(User p) {
		// TODO Auto-generated method stub
		return userDao.update(p);
	}

	public List<User> getPersons() {
		// TODO Auto-generated method stub
		return userDao.findall();
	}

	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.login(name, password);
	}

	public boolean checkUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.checkUserName(username);
	}

	public List<User> getPersons(int start, int end) {
		// TODO Auto-generated method stub
		return userDao.getPersons(start, end);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return this.getPersons().size();
	}

}
