package com.swu.shake.service;

import java.util.List;
/**
 * 评论业务服务类
 */

import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;

public interface CommentService {
	/** 新增评论 */
	public Comment remark(Comment comment);

	/** 删除评论 */
	public boolean remove(int cid);

	/** 修改评论 */
	public boolean modify(Comment comment);

	/** 得到某评论 */
	public Comment getComment(int cid);

	/** 获得某商品评论，分页 */
	public List<Comment> getCommentsByIid(int iid, int start, int end);

	/** 获得某用户评价的所有评论 */
	public List<Comment> getCommentsByUid(int uid);

	/** 查询某商品所有评论 */
	public List<Comment> findall(int iid);

	/** 获得某商品评论总数 */
	public long getCount(int iid);

}
