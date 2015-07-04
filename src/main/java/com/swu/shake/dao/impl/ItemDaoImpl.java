package com.swu.shake.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
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
			i.setPostImage(item.getPostImage());
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
	public boolean removeType(int tid) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String sql = "update t_item set  tid=null where tid=" + tid;
			session.createSQLQuery(sql).executeUpdate();
			transaction.commit();

			flag = true;
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();

		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;

	}

	@Override
	public Item findById(int id) {
		String hql = "from Item i where i.iid=" + id;
		return (Item) hibernateUtil.exeQuery(hql).get(0);
	}

	@Override
	public List<Item> findall() {
		String hql = "from Item order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByType(int tid) {
		String hql = "from Item i where i.itemtype.tid =" + tid
				+ " order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByUid(int uid) {
		String hql = "from Item i where i.user.uid =" + uid
				+ " order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByUidAndPage(int uid, int start, int end) {
		String hql = "from Item i where i.user.uid =" + uid
				+ " order by iid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public List<Item> getItemsByName(String str) {
		String hql = "from Item where iname like '%" + str
				+ "%' order by iid desc";
		return hibernateUtil.exeQuery(hql);
	}

	@Override
	public List<Item> getItemsByName(String str, int start, int end) {
		String hql = "from Item where iname like '%" + str
				+ "%' order by iid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public List<Item> getItems(int start, int end) {
		String hql = "select new com.swu.shake.model.Item(i.iid,i.iname,i.iprice,i.valid,i.onshelfdate,i.postImage,u.uid,u.name)  from Item i join i.user u order by i.iid desc";
		/*
		 * 成功的把商品展示压缩成一条sql Hibernate: select item0_.iid as col_0_0_,
		 * item0_.iname as col_1_0_, item0_.iprice as col_2_0_, item0_.valid as
		 * col_3_0_, item0_.onshelfdate as col_4_0_, item0_.postImage as
		 * col_5_0_, user1_.uid as col_6_0_, user1_.name as col_7_0_ from T_Item
		 * item0_ inner join T_User user1_ on item0_.uid=user1_.uid order by
		 * item0_.iid desc limit ?
		 */
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public List<Item> getItems(int tid, int start, int end) {
		String hql = "select new com.swu.shake.model.Item(i.iid,i.iname,i.iprice,i.valid,i.onshelfdate,i.postImage,u.uid,u.name)  from Item i join i.user u  where i.itemtype.tid="
				+ tid + " order by i.iid desc";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public long getCount() {
		String hql = "select count(*) from Item i";
		return hibernateUtil.exeCount(hql);
	}

	@Override
	public long getCount(int tid) {
		String hql = "select count(*) from Item i where i.itemtype.tid=" + tid;
		return hibernateUtil.exeCount(hql);
	}

	@Override
	public long getCountByUid(int uid) {
		String hql = "select count(*) from Item i where i.user.uid=" + uid;
		return hibernateUtil.exeCount(hql);
	}

	@Override
	public long getCount(String iname) {
		String hql = "select count(*) from Item i where i.iname like '%"
				+ iname + "%'";
		return hibernateUtil.exeCount(hql);
	}

}
