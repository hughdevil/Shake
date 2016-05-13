package com.swu.shake.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.swu.shake.log.CommentLog;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.Pager;
import com.swu.shake.model.User;
import com.swu.shake.service.CollectionService;
import com.swu.shake.service.CommentService;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.LikeService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.TimeUtil;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	private static final int PAGE_SIZE = 10;
	private static final int AUTHORISE_MODERATOR = 2;
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	CommentService commentService;
	ItemService itemService;
	UserService userService;
	CollectionService collectionService;
	LikeService likeService;

	public LikeService getLikeService() {
		return likeService;
	}

	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public CollectionService getCollectionService() {
		return collectionService;
	}

	@Autowired
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/remark", method = RequestMethod.GET)
	public String remark() {
		return "comment/remark";
	}

	@RequestMapping(value = "/remark", method = RequestMethod.POST)
	public ModelAndView remark(@RequestParam("itemid") int iid, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		String jumpUri = "";

		if (null != curuser && (null != userService.getUserById(curuser.getUid()))) {
			Item item = this.itemService.getItem(iid);
			if (null != item) {
				Comment comment = new Comment();
				comment.setItem(item);
				comment.setUser(curuser);
				comment.setContent(request.getParameter("content"));
				comment.setMarkDate(new Date());
				Comment newComment = commentService.remark(comment);

				if (null != newComment) {
					viewName = "comm/successJ";
					logger.info(new CommentLog(curuser.getUid(), newComment.getCid(), "REMARK", CommentLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
				} else {
					message = "留言成功";
					viewName = "comm/failureJ";
					logger.error(new CommentLog(curuser.getUid(), 0, "REMARK", CommentLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
				}

			} else {
				message = "该商品不存在或者已经删除";
				viewName = "comm/failure";
			}
		} else {
			message = "未登录";
			viewName = "comm/failureJ";
		}

		jumpUri = "item/" + iid + "/detail";
		request.setAttribute("message", message);
		request.setAttribute("jumpUri", jumpUri);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/{cid}/del", method = RequestMethod.GET)
	public String del(HttpSession session, Model model, @PathVariable(value = "cid") int cid) {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		String jumpUri = "";

		Comment comment = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "comm/failure";
		} else if (null != (comment = commentService.getComment(cid))) {
			boolean canDel = false;
			if (comment.getUser().getUid() == curuser.getUid()) {
				canDel = true;
			} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR) {
				if (null == comment.getUser().getRole()) {
					canDel = true;
				} else if (comment.getUser().getRole().getRlevel() < curuser.getRole().getRlevel()) {
					canDel = true;
				}
			}
			if (canDel) {
				if (commentService.remove(cid)) {
					viewName = "comm/successJ";
					logger.info(new CommentLog(curuser.getUid(), cid, "DEL", CommentLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
				} else {
					message = "系统错误，请稍后再试";
					viewName = "comm/failureJ";
					logger.error(new CommentLog(curuser.getUid(), cid, "DEL", CommentLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
				}
			} else {
				message = "非自己评论";
				viewName = "comm/failureJ";
				logger.warn(new CommentLog(curuser.getUid(), cid, "DEL", CommentLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
			jumpUri = "item/" + comment.getItem().getIid() + "/detail";
		} else {
			message = "评论不存在";
			viewName = "comm/failure";
		}
		model.addAttribute("jumpUri", jumpUri);
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{cid}/edit", method = RequestMethod.POST)
	public String edit(HttpSession session, Model model, HttpServletRequest request, @PathVariable(value = "cid") int cid) throws UnsupportedEncodingException {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		String jumpUri = "";
		Comment comment = null;

		if (null == curuser) {
			message = "未登录";
			viewName = "comm/failure";
		} else if (null != (comment = commentService.getComment(cid))) {
			boolean canEdit = false;
			if (comment.getUser().getUid() == curuser.getUid()) {
				canEdit = true;
			} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR) {
				if (null == comment.getUser().getRole()) {
					canEdit = true;
				} else if (comment.getUser().getRole().getRlevel() < curuser.getRole().getRlevel()) {
					canEdit = true;
				}
			}

			if (canEdit) {
				comment.setContent(request.getParameter("content"));
				if (commentService.modify(comment)) {
					viewName = "comm/successJ";
					logger.info(new CommentLog(curuser.getUid(), cid, "EDIT", CommentLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
				} else {
					message = "系统错误，请稍后再试";
					viewName = "comm/failureJ";
					logger.error(new CommentLog(curuser.getUid(), cid, "EDIT", CommentLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
				}
			} else {
				message = "非自己评论";
				viewName = "comm/failureJ";
				logger.warn(new CommentLog(curuser.getUid(), cid, "EDIT", CommentLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
			jumpUri = "item/" + comment.getItem().getIid() + "/detail";
		} else {
			message = "评论不存在";
			viewName = "comm/failure";
		}
		model.addAttribute("jumpUri", jumpUri);
		model.addAttribute("message", message);
		return viewName;

	}

	@RequestMapping(value = "/{iid}/all", params = "page", method = RequestMethod.GET)
	public String showDetail(@PathVariable(value = "iid") int iid, Model model, HttpServletRequest request, HttpSession session) {
		String viewName = "";
		int curpage = Integer.parseInt(request.getParameter("page"));
		User curuser = (User) session.getAttribute("user");
		Item item = null;
		if (null == curuser) {
			request.setAttribute("message", "未登录！需登录才能查看全部评论");
			viewName = "comm/failure";
		} else {

			if (null == (item = itemService.getItem(iid))) {
				viewName = "comm/failure";
				request.setAttribute("message", "童鞋，你确定有这个编号？");
			} else {
				Pager pager = new Pager(commentService.getCount(iid), PAGE_SIZE, curpage);
				item.setComments(commentService.getCommentsByIid(iid, pager.getStartRow(), PAGE_SIZE));

				boolean isMyCol = ((null != collectionService.isMyCol(curuser.getUid(), iid)) ? true : false);
				model.addAttribute("isMyCol", isMyCol);
				if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR) {
					model.addAttribute("isMODERATOR", true);
				}

				for (Comment c : item.getComments()) {
					c.setIsMyLike(likeService.isMyLike(curuser.getUid(), c.getCid()));
				}

				model.addAttribute("pager", pager);
				model.addAttribute("item", item);
				viewName = "comment/all";
			}
		}
		return viewName;
	}

}
