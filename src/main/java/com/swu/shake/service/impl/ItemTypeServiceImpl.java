package com.swu.shake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.ItemDao;
import com.swu.shake.dao.ItemTypeDao;
import com.swu.shake.model.ItemType;
import com.swu.shake.service.ItemTypeService;

@Service(value = "itemTypeService")
public class ItemTypeServiceImpl implements ItemTypeService {
	ItemTypeDao itemTypeDao;
	ItemDao itemDao;

	public ItemDao getItemDao() {
		return itemDao;
	}

	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public ItemTypeDao getItemTypeDao() {
		return itemTypeDao;
	}

	@Autowired
	public void setItemTypeDao(ItemTypeDao itemTypeDao) {
		this.itemTypeDao = itemTypeDao;
	}

	@Override
	public ItemType register(ItemType itemType) {
		return this.itemTypeDao.save(itemType);
	}

	@Override
	public boolean remove(int tid) {
		boolean flag = true;
		if (!itemDao.removeType(tid)) {
			flag = false;
		}
		if (!itemTypeDao.delete(tid)) {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean modify(ItemType itemType) {
		return itemTypeDao.update(itemType);
	}

	@Override
	public List<ItemType> getItemTypes() {
		return itemTypeDao.findall();
	}

	@Override
	public ItemType getItemTypeById(int tid) {
		return itemTypeDao.getItemTypeById(tid);
	}

	@Override
	public long getCount() {
		return itemTypeDao.getCount();
	}

}
