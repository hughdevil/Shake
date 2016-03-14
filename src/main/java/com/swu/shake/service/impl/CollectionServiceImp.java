package com.swu.shake.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.CollectionDao;
import com.swu.shake.model.Collection;
import com.swu.shake.model.Item;
import com.swu.shake.service.CollectionService;

@Service(value = "collectionService")
public class CollectionServiceImp implements CollectionService {

	CollectionDao collectionDao;

	public CollectionDao getCollectionDao() {
		return collectionDao;
	}

	@Autowired
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	@Override
	public Collection collect(Collection collection) {
		Collection coll = this.collectionDao.isMyCol(collection.getUser().getUid(), collection.getItem().getIid());
		if (null == coll) {
			coll = collectionDao.save(collection);
		}
		return coll;
	}

	@Transactional
	@Override
	public boolean remove(int[] ids) {
		boolean flag = true;
		for (int id : ids) {
			if (!this.collectionDao.deleteByColid(id)) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public List<Item> findall(int uid) {
		List<Collection> colls = this.collectionDao.findall(uid);
		List<Item> items = new ArrayList<Item>();
		for (Collection col : colls) {
			items.add(col.getItem());
		}
		return items;
	}

	@Override
	public Collection isMyCol(int uid, int iid) {
		return this.collectionDao.isMyCol(uid, iid);
	}

	@Override
	public List<Collection> getCollections(int uid, int start, int end) {
		return this.collectionDao.getCollections(uid, start, end);
	}

	@Override
	public long getCount(int uid) {
		return this.collectionDao.getCount(uid);
	}

}
