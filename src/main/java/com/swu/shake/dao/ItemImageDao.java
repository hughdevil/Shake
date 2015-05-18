package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.ItemImage;

/**
 * 用户访问数据对象接口
 * 
 */

public interface ItemImageDao {
	/** 产品图片保存 */
	public ItemImage save(ItemImage itemImage);

	/** 删除产品图片 */
	public boolean delete(int id);

}
