package com.swu.shake.service;

import java.util.List;
/**
 * 评论业务服务类
 */

import com.swu.shake.model.Collection;
import com.swu.shake.model.Comment;
import com.swu.shake.util.MsgException;

public interface CollectionService {
	/** 新增收藏 */
	public Collection collect(Comment comment);

	/** 删除收藏 */
	public boolean remove(int[] id);

	/** 查询某用户所有收藏 */
	public List<Comment> findall(int uid);

	/** 分页显示某用户所有收藏 */
	public List<Comment> getCollections(int uid, int start, int end);

	/** 获得某用户收藏总数 */
	public long getCount(int uid);

}
