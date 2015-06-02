package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Comment;

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
	public List<Comment> findall(int iid);

	/** 分页显示某商品所有评论 */
	public List<Comment> getComments(int iid, int start, int end);
	
	/** 获得某商品的评论总数*/
	public long getCount(int iid);

}
