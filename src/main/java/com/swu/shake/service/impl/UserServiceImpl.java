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
		User u = null;
		if (!checkUserName(user.getName())) {
			u = userDao.save(user);
		} else {
			throw new MsgException("用户名已经存在");
		}
		return u;
	}

	public boolean remove(String[] ids) {
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			return userDao.delete(Integer.parseInt(ids[i]));
		}
		return flag;

	}

	public boolean modify(User p) {
		return userDao.update(p);
	}

	public List<User> getPersons() {
		return userDao.findall();
	}

	public User login(String name, String password) {
		return userDao.login(name, password);
	}

	public boolean checkUserName(String username) {
		return userDao.checkUserName(username);
	}

	@Override
	public boolean checkUserId(int uid) {
		return userDao.checkUserId(uid);
	}

	public List<User> getPersons(int start, int end) {
		return userDao.getPersons(start, end);
	}

	public int getCount() {
		return this.getPersons().size();
	}

}
