package com.swu.shake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.ItemTypeDao;
import com.swu.shake.model.ItemType;
import com.swu.shake.service.ItemTypeService;

@Service(value = "itemTypeService")
public class ItemTypeServiceImpl implements ItemTypeService {
	ItemTypeDao itemTypeDao;

	public ItemTypeDao getItemTypeDao() {
		return itemTypeDao;
	}

	@Autowired
	public void setItemTypeDao(ItemTypeDao itemTypeDao) {
		this.itemTypeDao = itemTypeDao;
	}

	@Override
	public ItemType register(ItemType itemType) {
		// TODO Auto-generated method stub
		return this.itemTypeDao.save(itemType);
	}

	@Override
	public boolean remove(int tid) {
		// TODO Auto-generated method stub
		return itemTypeDao.delete(tid);
	}

	@Override
	public boolean modify(ItemType itemType) {
		// TODO Auto-generated method stub
		return itemTypeDao.update(itemType);
	}

	@Override
	public List<ItemType> getItemTypes() {
		// TODO Auto-generated method stub
		return itemTypeDao.findall();
	}

	@Override
	public ItemType getItemTypeById(int tid) {
		return itemTypeDao.getItemTypeById(tid);
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return itemTypeDao.getCount();
	}

}
