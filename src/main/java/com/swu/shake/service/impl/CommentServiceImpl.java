package com.swu.shake.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.dao.ItemDao;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.service.CommentService;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
	CommentDao commentDao;
	ItemDao itemDao;

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
	public boolean remove(int[] ids) {
		boolean flag = true;
		for (int id : ids) {
			if (!this.commentDao.delete(id)) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public Item getComments(int iid, int start, int end) {
		Item item = itemDao.findById(iid);
		List<Comment> comments = commentDao.getComments(iid, start, end);
		item.setComments(comments);
		return item;
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

}
