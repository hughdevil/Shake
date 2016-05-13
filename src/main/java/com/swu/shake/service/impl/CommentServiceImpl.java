package com.swu.shake.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.LikeDao;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.service.CommentService;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
	CommentDao commentDao;
	ItemDao itemDao;
	LikeDao likeDao;

	public LikeDao getLikeDao() {
		return likeDao;
	}

	@Autowired
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public Comment remark(Comment comment) {
		return commentDao.save(comment);
	}

	@Transactional
	@Override
	public boolean remove(int cid) {
		try {
			// 删除点赞表里对该评论点赞的记录
			this.likeDao.removeAllByCid(cid);

			this.commentDao.delete(cid);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<Comment> getCommentsByIid(int iid, int start, int end) {
		return commentDao.getComments(iid, start, end);
	}

	@Override
	public boolean modify(Comment comment) {
		return this.commentDao.update(comment);
	}

	@Override
	public Comment getComment(int cid) {
		return this.commentDao.getComment(cid);
	}

	@Override
	public List<Comment> findall(int iid) {
		return this.commentDao.findall(iid);
	}

	@Override
	public long getCount(int iid) {
		return this.commentDao.getCount(iid);
	}

	@Override
	public List<Comment> getCommentsByUid(int uid) {
		return this.commentDao.findallByUid(uid);
	}

}
