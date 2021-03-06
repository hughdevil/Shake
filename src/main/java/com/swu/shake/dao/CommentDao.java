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

	/** 删除评论通过商品ID */
	public boolean deleteByIid(int iid);

	/** 修改评论 */
	public boolean update(Comment comment);

	/** 点赞一次 */
	public boolean like(int cid);

	/** 取消点赞一次 */
	public boolean unlike(int cid);

	/** 获得单个评论 */
	public Comment getComment(int cid);

	/** 查询某商品所有评论 */
	public List<Comment> findall(int iid);

	/** 查询某用户进行评价的所有评论 */
	public List<Comment> findallByUid(int uid);

	/** 分页显示某商品所有评论 */
	public List<Comment> getComments(int iid, int start, int end);

	/** 获得某商品的评论总数 */
	public long getCount(int iid);

}
