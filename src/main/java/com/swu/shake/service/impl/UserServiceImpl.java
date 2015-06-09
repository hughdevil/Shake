package com.swu.shake.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.ItemImageDao;
import com.swu.shake.dao.UserDao;
import com.swu.shake.model.Item;
import com.swu.shake.model.User;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MsgException;

@Component(value = "userService")
public class UserServiceImpl implements UserService {
	UserDao userDao;
	ItemDao itemDao;
	CommentDao commentDao;
	ItemImageDao itemImageDao;

	public ItemDao getItemDao() {
		return itemDao;
	}

	public ItemImageDao getItemImageDao() {
		return itemImageDao;
	}

	@Resource(name = "itemImageDao")
	public void setItemImageDao(ItemImageDao itemImageDao) {
		this.itemImageDao = itemImageDao;
	}

	@Resource(name = "itemDao")
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource(name = "commentDao")
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/******************************/

	public User register(User user) throws MsgException {
		User u = null;
		if (!userDao.checkUserName(user.getName())) {
			u = userDao.save(user);
		} else {
			throw new MsgException("用户名已经存在");
		}
		return u;
	}

	/**
	 * 之后记得加事物处理
	 */
	public boolean remove(int uid) {
		boolean flag = true;
		// 删除此人评论
		commentDao.deleteByUid(uid);

		// 删除此人的所有发帖
		List<Item> items = itemDao.getItemsByUid(uid);
		for (Item item : items) {
			if (!this.commentDao.deleteByIid(item.getIid()))
				flag = false;
			if (!this.itemImageDao.deleteByIid(item.getIid()))
				flag = false;
			if (!this.itemDao.delete(item.getIid()))
				flag = false;
		}

		// 删除此人
		if (!userDao.delete(uid)) {
			flag = false;
		}
		return flag;
	}

	public boolean modify(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean modifyPwd(User user) {
		return userDao.updatePwd(user);
	}

	public List<User> getUsers() {
		return userDao.findall();
	}

	public User login(String name, String password) {
		return userDao.login(name, password);
	}

	public List<User> getUsers(String username) {
		return userDao.getUsersByName(username);
	}

	@Override
	public User getUserById(int uid) {
		return userDao.getUserById(uid);
	}

	public List<User> getUsers(int start, int end) {
		return userDao.getUsersByPage(start, end);
	}

	@Override
	public List<User> getUsers(String username, int start, int end) {
		return userDao.getUsersByPage(username, start, end);
	}

	public int getCount() {
		return this.getUsers().size();
	}

}
