package com.swu.shake.service;

import java.util.List;
/**
 * 收藏业务服务类
 */

import com.swu.shake.model.Collection;
import com.swu.shake.model.Item;

public interface CollectionService {
	/** 添加到收藏 */
	public Collection collect(Collection collection);

	/** 删除收藏 */
	public boolean remove(int[] ids);
	
	/** 删除用户的所有收藏**/
	public boolean removeByUid(int uid);

	/** 查询某用户所有收藏商品 */
	public List<Item> findall(int uid);

	/** 查询某用户是否收藏过改商品 */
	public Collection isMyCol(int uid, int iid);

	/** 分页显示某用户所有收藏 */
	public List<Collection> getCollections(int uid, int start, int end);

	/** 获得某用户收藏总数 */
	public long getCount(int uid);

}
