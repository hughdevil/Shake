package com.swu.shake.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.CollectionDao;
import com.swu.shake.dao.CommentDao;
import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.ItemImageDao;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.service.CommentService;
import com.swu.shake.service.ItemService;
import com.swu.shake.util.MsgException;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {
	ItemDao itemDao;
	ItemImageDao itemImageDao;
	CollectionDao collectionDao;
	CommentService commentService;

	public CollectionDao getCollectionDao() {
		return collectionDao;
	}

	@Autowired
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public ItemImageDao getItemImageDao() {
		return itemImageDao;
	}

	@Autowired
	public void setItemImageDao(ItemImageDao itemImageDao) {
		this.itemImageDao = itemImageDao;
	}

	@Override
	public Item register(Item item) throws MsgException {
		Item i = itemDao.save(item);
		if (null == i) {
			throw new MsgException("商品发布失败了！");
		}
		return i;
	}

	/**
	 * 删除，要用Spring事务级处理；
	 */
	@Transactional
	@Override
	public boolean remove(int iid) {
		try {
			// 删除该商品下的所有评论
			List<Comment> cs = this.commentService.findall(iid);
			for (Comment c : cs) {
				this.commentService.remove(c.getCid());
			}
			this.collectionDao.deleteByIid(iid);
			this.itemImageDao.deleteByIid(iid);
			this.itemDao.delete(iid);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public boolean modify(Item item) {
		return itemDao.update(item);
	}

	@Override
	public List<Item> getItems() {
		return this.itemDao.findall();
	}

	@Override
	public List<Item> getItems(int uid) {
		return itemDao.getItemsByUid(uid);
	}

	@Override
	public List<Item> getSomebodyItemsByPage(int uid, int start, int end) {
		return itemDao.getItemsByUidAndPage(uid, start, end);
	}

	@Override
	public Item getItem(int id) {
		return itemDao.findById(id);
	}

	@Override
	public Item getDetail(int iid) {
		Item item = itemDao.findById(iid);
		List<ItemImage> images = itemImageDao.findall(iid);
		List<Comment> comments = commentService.getCommentsByIid(iid, 0, 10);
		item.setItemImages(new HashSet<ItemImage>(images));
		item.setComments(comments);

		return item;
	}

	@Override
	public List<ItemImage> getImgs(int id) {
		return itemImageDao.findall(id);
	}

	@Override
	public List<Item> getItems(int start, int end) {
		return itemDao.getItems(start, end);
	}

	@Override
	public List<Item> getItems(int tid, int start, int end) {
		return itemDao.getItems(tid, start, end);
	}

	@Override
	public long getCount(int tid) {
		return itemDao.getCount(tid);
	}

	@Override
	public List<Item> getItemsByType(int tid) {
		return this.itemDao.getItemsByType(tid);
	}

	@Override
	public List<Item> getItemsByName(String name) {
		return this.itemDao.getItemsByName(name);
	}

	@Override
	public List<Item> getItemsByName(String name, int start, int end) {
		return this.itemDao.getItemsByName(name, start, end);
	}

	@Override
	public long getCount() {
		return itemDao.getCount();
	}

	@Override
	public long getCount(String iname) {
		return this.itemDao.getCount(iname);
	}

	@Override
	public boolean clearUnuserfulImg() {
		return this.itemImageDao.delete();
	}

	@Override
	public long getCountByUid(int uid) {
		return this.itemDao.getCountByUid(uid);
	}

}
