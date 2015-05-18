package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;

/**
 * 用户访问数据对象接口
 * 
 */

public interface CommentDao {
	/** 评论注册 */
	public Comment save(Comment comment);

	/** 删除评论 */
	public boolean delete(int id);

	/** 修改评论 */
	public boolean update(Comment comment);

	/** 查询某商品所有评论 */
	public List<Item> findall(int iid);

	/** 分页显示某商品所有评论 */
	public List<Item> getComments(int iid, int start, int end);

}
