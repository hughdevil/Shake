package com.swu.shake.controller;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.User;
import com.swu.shake.service.CommentService;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MsgException;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	private static final int PAGE_SIZE = 10;
	private static final Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	CommentService commentService;
	ItemService itemService;
	UserService userService;

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
	public ModelAndView remark(@RequestParam("itemid") int iid,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		if (null != curuser && userService.checkUserId(curuser.getUid())) {
			Item item = this.itemService.getItem(iid);
			if (null != item) {
				Comment comment = new Comment();
				comment.setItem(item);
				comment.setUser(curuser);
				comment.setContent(request.getParameter("content"));
				comment.setMarkDate(new Date());
				commentService.remark(comment);

				message = "mark 成功";
				viewName = "comm/success";
			} else {
				message = "该商品不存在或者已经删除";
				viewName = "comm/failure";
			}
		} else {
			message = "未登录";
			viewName = "comm/failure";
		}
		request.setAttribute("message", message);
		mav.setViewName(viewName);
		return mav;
	}
}
