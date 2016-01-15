package com.swu.shake.service;

import java.util.List;
/**
 * 评论业务服务类
 */

import com.swu.shake.model.Comment;
import com.swu.shake.util.MsgException;

public interface CommentService {
	/** 新增评论 */
	public Comment remark(Comment comment);

	/** 删除评论 */
	public boolean remove(int[] id);

	/** 修改评论 */
	public boolean modify(Comment comment);

	/** 查询某商品所有评论 */
	public List<Comment> findall(int iid);

	/** 分页显示某商品所有评论 */
	public List<Comment> getComments(int iid, int start, int end);

	/** 获得某商品评论总数 */
	public long getCount(int iid);

}
