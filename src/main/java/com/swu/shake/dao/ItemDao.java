package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Item;

/**
 * 用户访问数据对象接口
 * 
 */

public interface ItemDao {
	/** 产品注册 */
	public Item save(Item item);

	/** 删除产品 */
	public boolean delete(int id);

	/** 修改产品 */
	public boolean update(Item item);

	/** 查询所有产品 */
	public List<Item> findall();

	/** 检测产品是否存在,存在返回true，否则返回false */
	public List<Item> getItemsByName(String str);

	/** 检测产品是否存在,存在返回true，否则返回false */
	public List<Item> getItemsByName(String str, int start, int end);

	/** 分页显示所有产品 */
	public List<Item> getItems(int start, int end);

	/** 获得商品总数 */
	public long getCount();

}
