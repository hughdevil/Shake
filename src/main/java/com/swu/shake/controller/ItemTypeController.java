package com.swu.shake.controller;

import java.util.List;

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

import com.swu.shake.log.ItemTypeLog;
import com.swu.shake.model.ItemType;
import com.swu.shake.model.User;
import com.swu.shake.service.ItemTypeService;
import com.swu.shake.util.TimeUtil;

@Controller
@RequestMapping(value = "/item/type")
public class ItemTypeController {
	private static final Logger logger = LoggerFactory.getLogger(ItemTypeController.class);
	private static final int AUTHORISE_ADMIN = 4;

	private ItemTypeService itemTypeService;

	public ItemTypeService getItemTypeService() {
		return itemTypeService;
	}

	@Autowired
	public void setItemTypeService(ItemTypeService itemTypeService) {
		this.itemTypeService = itemTypeService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) {
		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {

			List<ItemType> its = itemTypeService.getItemTypes();
			model.addAttribute("itemtypes", its);

			model.addAttribute("itemtype", new ItemType());
			viewName = "/item/type/add";
		} else {
			message = "权限不够";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, Model model, ItemType itemtype) {
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			ItemType itemType = itemTypeService.register(itemtype);
			if (null != itemType) {
				viewName = "/comm/successJ";
				logger.info(new ItemTypeLog(curuser.getUid(), itemType.getTid(), "ADD", ItemTypeLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
			} else {
				viewName = "/comm/failureJ";
				message = "系统错误， 稍后重试";
				logger.error(new ItemTypeLog(curuser.getUid(), 0, "ADD", ItemTypeLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
			jumpUri = "/item/type/list";
		} else {
			message = "权限不够";
			viewName = "/comm/failure";
		}

		model.addAttribute("message", message);
		model.addAttribute("jumpUri", jumpUri);
		return viewName;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session, Model model) {
		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			List<ItemType> its = itemTypeService.getItemTypes();
			model.addAttribute("itemtypes", its);
			viewName = "/item/type/list";
		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{tid}/edit", method = RequestMethod.GET)
	public String edit(HttpSession session, Model model, @PathVariable("tid") int tid) {
		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {

			List<ItemType> its = itemTypeService.getItemTypes();
			model.addAttribute("itemtypes", its);

			ItemType it = itemTypeService.getItemTypeById(tid);
			model.addAttribute("itemtype", it);
			viewName = "/item/type/edit";
		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpSession session, HttpServletRequest request) {
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			ItemType it = new ItemType();
			int tid = Integer.parseInt(request.getParameter("tid"));
			it.setTid(tid);
			it.setTname(request.getParameter("tname"));
			it.setTdesc(request.getParameter("tdesc"));
			if (itemTypeService.modify(it)) {
				viewName = "/comm/successJ";
				logger.info(new ItemTypeLog(curuser.getUid(), tid, "EDIT", ItemTypeLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
			} else {
				message = "系统错误， 稍后重试";
				viewName = "/comm/failureJ";
				logger.error(new ItemTypeLog(curuser.getUid(), tid, "EDIT", ItemTypeLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
			jumpUri = "/item/type/list";

		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		request.setAttribute("jumpUri", jumpUri);
		request.setAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{tid}/del", method = RequestMethod.GET)
	public String del(HttpSession session, Model model, @PathVariable("tid") int tid) {
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			if (itemTypeService.remove(tid)) {
				viewName = "/comm/successJ";
				logger.error(new ItemTypeLog(curuser.getUid(), tid, "DEL", ItemTypeLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
			} else {
				message = "系统错误， 稍后重试";
				viewName = "/comm/failureJ";
				logger.error(new ItemTypeLog(curuser.getUid(), tid, "DEL", ItemTypeLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
			jumpUri = "/item/type/list";

		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		model.addAttribute("jumpUri", jumpUri);
		model.addAttribute("message", message);
		return viewName;
	}
}
