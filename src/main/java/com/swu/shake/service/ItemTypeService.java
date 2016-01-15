package com.swu.shake.service;

import java.util.List;
/**
 * 产品业务服务类
 */

import com.swu.shake.model.ItemType;

public interface ItemTypeService {

	/** 增加商品种类 */
	public ItemType register(ItemType itemType);

	/** 删除商品种类 */
	public boolean remove(int tid);

	/** 修改商品种类 */
	public boolean modify(ItemType itemType);

	/** 查询所有商品种类 */
	public List<ItemType> getItemTypes();

	/** 得到商品种类总数 */
	public long getCount();
	
	/** 通过ID查询单个类型*/
	public ItemType getItemTypeById(int tid);

}
