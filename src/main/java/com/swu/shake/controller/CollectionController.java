package com.swu.shake.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.swu.shake.model.Collection;
import com.swu.shake.model.Comment;
import com.swu.shake.model.Item;
import com.swu.shake.model.User;
import com.swu.shake.service.CollectionService;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.Status;

/**
 * @author Shuai
 *
 */
@Controller
@RequestMapping(value = "/collect")
public class CollectionController {
	private static final int AUTHORISE_ADMIN = 4;
	private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);

	CollectionService collectionService;
	UserService userService;
	ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CollectionService getCollectionService() {
		return collectionService;
	}

	@Autowired
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	@RequestMapping(value = "/{iid}/collect", method = RequestMethod.GET)
	@ResponseBody
	public Status collect(@PathVariable(value = "iid") int iid, HttpSession session) {
		User curuser = (User) session.getAttribute("user");
		Status status = null;
		if (null != curuser) {
			Item item = this.itemService.getItem(iid);
			Collection coll = new Collection();
			coll.setUser(curuser);
			coll.setItem(item);
			coll.setMarkDate(new Date());
			collectionService.collect(coll);
			status = new Status(true, "收藏成功");
		} else {
			status = new Status(false, "收藏失败，请先登录");
		}
		return status;
	}

	@RequestMapping(value = "/{iid}/uncollect", method = RequestMethod.GET)
	@ResponseBody
	public Status uncollect(@PathVariable(value = "iid") int iid, HttpSession session) {
		User curuser = (User) session.getAttribute("user");
		Status status = null;
		if (null != curuser) {
			Collection coll = collectionService.isMyCol(curuser.getUid(), iid);
			if (null != coll) {
				collectionService.remove(new int[] { coll.getColid() });
			}
			status = new Status(true, "取消收藏成功");
		} else {
			status = new Status(false, "取消收藏失败，请先登录");
		}
		return status;
	}

	@RequestMapping(value = "/{uid}/post", method = RequestMethod.GET)
	public ModelAndView post(@PathVariable(value = "uid") int uid, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		String jumpUri = "";

		//权限判断
		if ((null != curuser && curuser.getUid() == uid)
				|| (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN)) {
			List<Item> itemList = collectionService.findall(uid);
			mav.addObject("itemList", itemList);
			viewName = "collect/post";
		} else {
			message = "未登录";
			viewName = "comm/failure";
		}

		request.setAttribute("message", message);
		request.setAttribute("jumpUri", jumpUri);
		mav.setViewName(viewName);
		return mav;
	}

}
