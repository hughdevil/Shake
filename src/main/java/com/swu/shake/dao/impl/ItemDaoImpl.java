package com.swu.shake.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.swu.shake.dao.ItemDao;
import com.swu.shake.model.Item;
import com.swu.shake.util.HibernateUtil;

/**
 * @author Hugh
 *
 */
@Repository(value = "itemDao")
public class ItemDaoImpl implements ItemDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	@Resource(name = "hibernateUtil")
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		Item i = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			i = (Item) session.load(Item.class, session.save(item));
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return i;
	}

	@Override
	public boolean delete(int id) {
		String hql = "delete from Item where iid=" + id;
		return hibernateUtil.exeDelete(hql);
	}

	@Override
	public boolean update(Item item) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			Item i = (Item) session.get(Item.class, item.getIid());
			i.setIname(item.getIname());
			i.setiNumber(item.getiNumber());
			i.setIprice(item.getIprice());
			i.setIdesc(item.getIdesc());
			i.setComments(item.getComments());
			i.setItemImages(item.getItemImages());
			i.setItemtype(item.getItemtype());
			i.setValid(item.isValid());

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
	public List<Item> findall() {
		// TODO Auto-generated method stub
		String hql = "from Item order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByName(String str) {
		// TODO Auto-generated method stub
		String hql = "from Item where iname like '%" + str
				+ "%' order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByName(String str, int start, int end) {
		// TODO Auto-generated method stub
		String hql = "from Item where iname like '%" + str
				+ "%' order by iid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public List<Item> getItems(int start, int end) {
		// TODO Auto-generated method stub
		String hql = "select i.iid,i.iname,i.iprice,i.isValid,i.onshelfdate,i.itemImages,i.user from Item i order by iid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Item i";
		return hibernateUtil.exeCount(hql);
	}

}
