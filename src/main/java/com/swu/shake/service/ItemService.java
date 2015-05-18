package com.swu.shake.service;

import java.util.List;
/**
 * 用户业务服务类
 * @author 王伟杰
 */


import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
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
	
	/** 查询所有商品分页 */
	public List<Item> getItems(int start, int end);

	/** 通过姓名 检测商品是否存在 */
	public List<Item> getItemsByName(String name);

	/** 通过姓名 检测商品是否存在 */
	public List<Item> getItemsByName(String name, int start, int end);

	/** 得到商品总数 */
	public long getCount();
}
