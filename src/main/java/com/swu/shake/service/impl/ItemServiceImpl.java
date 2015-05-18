package com.swu.shake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.ItemImageDao;
import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.service.ItemService;
import com.swu.shake.util.MsgException;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {
	ItemDao itemDao;
	ItemImageDao itemImageDao;

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
		// TODO Auto-generated method stub
		Item i = itemDao.save(item);
		if (null == i) {
			throw new MsgException("商品发布失败了！");
		}
		return i;
	}

	@Override
	public boolean remove(int[] ids) {
		// TODO Auto-generated method stub
		boolean flag = true;
		for (int id : ids) {
			if (!this.itemDao.delete(id))
				flag = false;
		}
		return flag;
	}

	@Override
	public boolean modify(Item item) {
		// TODO Auto-generated method stub
		return itemDao.update(item);
	}

	@Override
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		return this.itemDao.findall();
	}

	@Override
	public List<Item> getItems(int start, int end) {
		// TODO Auto-generated method stub
		return itemDao.getItems(start, end);
	}

	@Override
	public List<Item> getItemsByName(String name) {
		// TODO Auto-generated method stub
		return this.itemDao.getItemsByName(name);
	}

	@Override
	public List<Item> getItemsByName(String name, int start, int end) {
		// TODO Auto-generated method stub
		return this.itemDao.getItemsByName(name, start, end);
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return itemDao.getCount();
	}

}
