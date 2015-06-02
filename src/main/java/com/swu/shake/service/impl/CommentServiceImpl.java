package com.swu.shake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.model.Comment;
import com.swu.shake.service.CommentService;
import com.swu.shake.util.MsgException;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
	CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public Comment remark(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.save(comment);
	}

	@Override
	public boolean remove(int[] ids) {
		// TODO Auto-generated method stub
		boolean flag = true;
		for (int id : ids) {
			if (!this.commentDao.delete(id)) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean modify(Comment comment) {
		// TODO Auto-generated method stub
		return this.commentDao.update(comment);
	}

	@Override
	public List<Comment> findall(int iid) {
		// TODO Auto-generated method stub
		return this.commentDao.findall(iid);
	}

	@Override
	public List<Comment> getComments(int iid, int start, int end) {
		// TODO Auto-generated method stub
		return this.commentDao.getComments(iid, start, end);
	}

	@Override
	public long getCount(int iid) {
		// TODO Auto-generated method stub
		return this.commentDao.getCount(iid);
	}

}
