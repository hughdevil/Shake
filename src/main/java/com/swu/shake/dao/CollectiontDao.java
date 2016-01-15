package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Comment;

/**
 * 用户访问数据对象接口
 * 
 */

public interface CollectiontDao {
	/** 收藏 */
	public Comment save(Comment comment);

	/** 删除收藏通过收藏ID */
	public boolean deleteByColid(int colid);

	/** 删除收藏通过收藏人 */
	public boolean deleteByUid(int uid);

	/** 查询某用户所有收藏 */
	public List<Comment> findall(int uid);
	
	/** 分页显示某用户所有收藏 */
	public List<Comment> getCollections(int uid, int start, int end);

	/** 获得某用户的收藏总数 */
	public long getCount(int uid);

}
