package com.swu.shake.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metamodel.binding.HibernateTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.CommentDao;
import com.swu.shake.model.Comment;
import com.swu.shake.util.HibernateUtil;

/**
 * sqlinject
 */
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
		String hql = "delete from Comment where cid=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean deleteByIid(int iid) {
		String hql = "delete from Comment c where c.item.iid=" + iid;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(Comment comment) {
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
		String hql = "from Comment c where c.item.iid=" + iid;
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public Comment getComment(int cid) {
		String hql = "from Comment c where c.cid=" + cid;
		List<Comment> comments = hibernateUtil.exeQuery(hql);
		if (comments != null && !comments.isEmpty()) {
			return comments.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> getComments(int iid, int start, int end) {
		String hql = "from Comment c where c.item.iid=" + iid + "  order by c.cid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public long getCount(int iid) {
		String hql = "select count(*) from Comment c where c.item.iid=" + iid;
		return this.hibernateUtil.exeCount(hql);
	}

	@Override
	public boolean like(int cid) {
		boolean flag = false;
		try {
			Session session = hibernateUtil.getSessionFactory().getCurrentSession();
			Comment c = (Comment) session.get(Comment.class, cid);
			c.setLikes((c.getLikes() + 1));
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean unlike(int cid) {
		boolean flag = false;
		try {
			Session session = hibernateUtil.getSessionFactory().getCurrentSession();
			Comment c = (Comment) session.get(Comment.class, cid);
			c.setLikes((c.getLikes() - 1));
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<Comment> findallByUid(int uid) {
		String hql = "from Comment c where c.user.uid=" + uid;
		return hibernateUtil.exeQuery(hql);
	}

}
