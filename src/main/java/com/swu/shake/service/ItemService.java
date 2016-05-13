package com.swu.shake.service;

import java.util.List;
/**
 * 产品业务服务类
 */

import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.util.MsgException;

public interface ItemService {
	/** 发布商品 */
	public Item register(Item item) throws MsgException;

	/** 删除商品 */
	public boolean remove(int iid);

	/** 修改商品 */
	public boolean modify(Item item);

	/** 查询所有商品 */
	public List<Item> getItems();

	/** 通过ID查找单个商品 */
	public Item getItem(int iid);

	/** 通过ID查找单个商品细节 */
	public Item getDetail(int iid);

	/** 通过ID查找单个商品的所有图片 */
	public List<ItemImage> getImgs(int iid);

	/** 清空不需要的图片 */
	public boolean clearUnuserfulImg();

	/** 通过UID查找此人的所有商品 */
	public List<Item> getItems(int uid);

	/** 通过UID查找此人的所有商品 分页 */
	public List<Item> getSomebodyItemsByPage(int uid, int start, int end);

	/** 得到所有商品分页 */
	public List<Item> getItems(int start, int end);

	/** 得到某类所有商品分页 */
	public List<Item> getItems(int tid, int start, int end);

	/** 得到某个种类的所有商品 */
	public List<Item> getItemsByType(int tid);

	/** 通过姓名 检索商品 模糊 */
	public List<Item> getItemsByName(String name);

	/** 通过姓名 检索商品 模糊 分页 */
	public List<Item> getItemsByName(String name, int start, int end);

	/** 得到商品总数 */
	public long getCount();

	/** 得到某类商品总数 */
	public long getCount(int tid);

	/** 得到某人商品总数 */
	public long getCountByUid(int uid);

	/** 通过商品名得到总数 模糊 */
	public long getCount(String iname);

}
