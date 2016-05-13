package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Collection;

/**
 * 用户访问数据对象接口
 * 
 */

public interface CollectionDao {
	/** 收藏 */
	public Collection save(Collection collection);

	/** 删除收藏通过收藏ID */
	public boolean deleteByColid(int colid);

	/** 删除收藏通过收藏人 */
	public boolean deleteByUid(int uid);

	/** 删除收藏通过收藏商品 */
	public boolean deleteByIid(int iid);

	/** 查询某用户所有收藏 */
	public List<Collection> findall(int uid);

	/** 查询某用户是否收藏过改商品 */
	public Collection isMyCol(int uid, int iid);

	/** 分页显示某用户所有收藏 */
	public List<Collection> getCollections(int uid, int start, int end);

	/** 获得某用户的收藏总数 */
	public long getCount(int uid);

}
