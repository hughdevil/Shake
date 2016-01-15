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

	/** 删除多余的图片 */
	public boolean delete();

	/** 删除产品图片 */
	public boolean delete(int iiid);

	/** 删除图片通过商品ID */
	public boolean deleteByIid(int iid);


	/** 查找产品图片 */
	public List<ItemImage> findall(int iid);

}
