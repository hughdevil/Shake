package com.swu.shake.service;

import java.util.List;
/**
 * 产品业务服务类
 */

import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.model.ItemType;
import com.swu.shake.util.MsgException;

public interface ItemService {
	/** 发布商品 */
	public Item register(Item item) throws MsgException;

	/** 删除商品 */
	public boolean remove(int[] ids);

	/** 修改商品 */
	public boolean modify(Item item);

	/** 查询所有商品 */
	public List<Item> getItems();

	/** 通过ID查找单个商品 */
	public Item getItem(int id);

	/** 通过ID查找单个商品细节 */
	public Item getDetail(int id);

	/** 通过ID查找单个商品的所有图片 */
	public List<ItemImage> getImgs(int id);

	/** 清空不需要的图片 */
	public boolean clearUnuserfulImg();

	/** 得到所有商品分页 */
	public List<Item> getItems(int start, int end);

	/** 得到某类所有商品分页 */
	public List<Item> getItems(int tid, int start, int end);

	/** 得到某个种类的所有商品 */
	public List<Item> getItemsByType(int tid);

	/** 通过姓名 检测商品是否存在 */
	public List<Item> getItemsByName(String name);

	/** 通过姓名 检测商品是否存在 分页 */
	public List<Item> getItemsByName(String name, int start, int end);

	/** 得到商品总数 */
	public long getCount();

	/** 得到某类商品总数 */
	public long getCount(int tid);

}
