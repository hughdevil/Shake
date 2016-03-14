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

import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.Pager;
import com.swu.shake.model.User;
import com.swu.shake.service.CollectionService;
import com.swu.shake.service.CommentService;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.UserService;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	private static final int PAGE_SIZE = 10;
	private static final int AUTHORISE_ADMIN = 4;
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	CommentService commentService;
	ItemService itemService;
	UserService userService;
	CollectionService collectionService;

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

		jumpUri = "item/" + iid + "/detail";
		request.setAttribute("message", message);
		request.setAttribute("jumpUri", jumpUri);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/{cid}/del", method = RequestMethod.GET)
	public String del(HttpSession session, Model model, @PathVariable(value = "cid") int cid) {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		Comment comment = commentService.getComment(cid);

		if (null == curuser) {
			message = "未登录";
			viewName = "comm/failure";
		} else if (comment != null && comment.getUser().getUid() == curuser.getUid()
				|| (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN)) {
			commentService.remove(new int[] { cid });
			message = "删除成功";
			viewName = "comm/success";
		} else {
			message = "非自我评论或角色权限等级不足";
			viewName = "comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{cid}/edit", method = RequestMethod.POST)
	public String edit(HttpSession session, Model model, HttpServletRequest request,
			@PathVariable(value = "cid") int cid) throws UnsupportedEncodingException {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		Comment comment = commentService.getComment(cid);

		if (null == curuser) {
			message = "未登录";
			viewName = "comm/failure";
		} else if (comment != null && comment.getUser().getUid() == curuser.getUid()
				|| (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN)) {
			comment.setContent(request.getParameter("content"));
			commentService.modify(comment);
			message = "修改成功";
			viewName = "comm/success";
		} else {
			message = "非自我评论或角色权限等级不足";
			viewName = "comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;

	}

	@RequestMapping(value = "/{iid}/all", params = "page", method = RequestMethod.GET)
	public String showDetail(@PathVariable(value = "iid") int iid, Model model, HttpServletRequest request,
			HttpSession session) {
		String viewName = "";
		int curpage = Integer.parseInt(request.getParameter("page"));
		User curuser = (User) session.getAttribute("user");

		if (curuser != null) {
			if (null == itemService.getItem(iid)) {
				viewName = "comm/failure";
				request.setAttribute("message", "童鞋，你确定有这个编号？");
			} else {
				Pager pager = new Pager(commentService.getCount(iid), PAGE_SIZE, curpage);
				Item item = commentService.getComments(iid, pager.getStartRow(), PAGE_SIZE);

				boolean isMyCol = null != collectionService.isMyCol(curuser.getUid(), iid) ? true : false;
				model.addAttribute("isMyCol", isMyCol);
				if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
					model.addAttribute("isADMIN", true);
				}

				model.addAttribute("pager", pager);
				model.addAttribute("item", item);
				viewName = "comment/all";
			}
		} else {
			request.setAttribute("message", "未登录！需登录才能查看全部评论");
			viewName = "comm/failure";
		}
		return viewName;
	}

}
