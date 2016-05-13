package com.swu.shake.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.swu.shake.dao.CollectionDao;
import com.swu.shake.dao.CommentDao;
import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.ItemImageDao;
import com.swu.shake.dao.LikeDao;
import com.swu.shake.dao.UserDao;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.User;
import com.swu.shake.service.CommentService;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MsgException;

@Component(value = "userService")
public class UserServiceImpl implements UserService {
	UserDao userDao;
	ItemDao itemDao;
	CommentDao commentDao;
	CommentService commentService;
	ItemImageDao itemImageDao;
	ItemService itemService;
	LikeDao likeDao;
	CollectionDao collectionDao;

	public CommentService getCommentService() {
		return commentService;
	}

	@Resource(name = "commentService")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public CollectionDao getCollectionDao() {
		return collectionDao;
	}

	@Resource(name = "collectionDao")
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	public LikeDao getLikeDao() {
		return likeDao;
	}

	@Resource(name = "likeDao")
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	public ItemService getItemService() {
		return itemService;
	}

	@Resource(name = "itemService")
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

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
	@Transactional
	@Override
	public boolean remove(int uid) {
		try {

			/**
			 * 收藏和点赞没有被作为外键引用，直接用dao
			 */
			// 删除此人的所有收藏
			collectionDao.deleteByUid(uid);
			// 删除此人所有的评论点赞，但是不影响他人评论店点赞总数
			likeDao.removeAllByUid(uid);

			/**
			 * 评论、商品有外键引用，使用servcie;
			 */
			// 删除此人生产的所有评论
			List<Comment> comments = commentService.getCommentsByUid(uid);
			for (Comment c : comments) {
				commentService.remove(c.getCid());
			}

			// 删除此人的所有发帖
			List<Item> items = itemDao.getItemsByUid(uid);
			for (Item item : items) {
				itemService.remove(item.getIid());
			}

			// 删除此人
			userDao.delete(uid);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
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
