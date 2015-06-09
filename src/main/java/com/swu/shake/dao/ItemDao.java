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

	/** 根据ID查找单个产品 */
	public Item findById(int id);

	/** 根据商品种类查找商品 */
	public List<Item> getItemsByType(int tid);

	/** 根据发布人查找商品 */
	public List<Item> getItemsByUid(int uid);

	/** 根据姓名查找商品 */
	public List<Item> getItemsByName(String str);

	/** 根据姓名查找商品 分页 */
	public List<Item> getItemsByName(String str, int start, int end);

	/** 分页显示所有产品 */
	public List<Item> getItems(int start, int end);

	/** 分页显示某类所有产品 */
	public List<Item> getItems(int tid, int start, int end);

	/** 获得商品总数 */
	public long getCount();

	/** 获得某类商品总数 */
	public long getCount(int tid);

	/** 通过商品名获得商品总数 模糊 */
	public long getCount(String iname);

}
