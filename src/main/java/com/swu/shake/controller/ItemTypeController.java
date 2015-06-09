package com.swu.shake.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swu.shake.model.ItemType;
import com.swu.shake.model.User;
import com.swu.shake.service.ItemTypeService;

@Controller
@RequestMapping(value = "/item/type")
public class ItemTypeController {
	private static final Logger logger = LoggerFactory
			.getLogger(ItemTypeController.class);
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
		} else if (curuser.getRole() != null
				&& curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			model.addAttribute("itemtype", new ItemType());
			viewName = "/item/type/add";
		} else {
			logger.info("权限不够");
			message = "权限不够";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, Model model, ItemType itemtype) {
		String viewName = "";
		if (null != itemTypeService.register(itemtype)) {
			viewName = "/comm/success";
		} else {
			viewName = "/comm/failure";
		}
		return viewName;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session, Model model) {
		String viewName = "";
		viewName = "/item/type/list";
		return viewName;
	}
}
