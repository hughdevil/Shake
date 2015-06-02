package com.swu.shake.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.model.Comment;
import com.swu.shake.util.HibernateUtil;

@Repository(value = "commentDao")
public class CommentDaoImpl implements CommentDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Autowired
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Comment c = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			c = (Comment) session.load(Comment.class, session.save(comment));
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return c;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from Comment where cid=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(Comment comment) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			Comment c = (Comment) session.load(Comment.class, comment.getCid());
			c.setContent(comment.getContent());

			transaction.commit();

			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@Override
	public List<Comment> findall(int iid) {
		String hql = "from Comment c where c.item.iid= "+iid;
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Comment> getComments(int iid, int start, int end) {
		// TODO Auto-generated method stub
		String hql = "from Comment";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public long getCount(int iid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Comment c";
		return this.hibernateUtil.exeCount(hql);
	}

}
