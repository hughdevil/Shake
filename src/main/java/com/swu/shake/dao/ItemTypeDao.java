package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.ItemType;

/**
 * 用户访问数据对象接口
 * 
 */

public interface ItemTypeDao {
	/** 产品类型注册 */
	public ItemType save(ItemType itemType);

	/** 删除产品类型 */
	public boolean delete(int id);

	/** 修改产品类型 */
	public boolean update(ItemType itemType);

	public ItemType getItemTypeById(int id);

	/** 查询所有产品类型 */
	public List<ItemType> findall();

	/** 检测产品是否存在,存在返回true，否则返回false */
	public List<ItemType> getItemTypesByName(String str, int start, int end);

}