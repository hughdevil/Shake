package com.swu.shake.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swu.shake.model.Role;
import com.swu.shake.model.User;
import com.swu.shake.service.RoleService;
import com.swu.shake.util.RlevelUtil;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	private static final int AUTHORISE_ADMIN = 4;
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null
				&& curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			int rlevel = curuser.getRole().getRlevel();
			Map<String, String> rlevels = null;
			try {
				rlevels = RlevelUtil.getAbleLevel(rlevel);
			} catch (UnsupportedEncodingException e) {
				model.addAttribute("message", "配置文件编码出错");
				e.printStackTrace();
			}
			model.addAttribute("rlevels", rlevels);
			model.addAttribute("role", new Role());
			viewName = "role/add";
		} else {
			viewName = "comm/failure";
			message = "权限不够";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(
			@RequestParam(value = "rlevelcode", required = true) String rlevelcode,
			HttpServletRequest request, HttpSession session, Model model,
			Role role) {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null
				&& curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			int rlevel = RlevelUtil.getLevel(rlevelcode);
			role.setRlevel(rlevel);
			roleService.register(role);
			viewName = "comm/success";
		} else {
			viewName = "comm/failure";
			message = "权限不够";
		}
		model.addAttribute("message", message);
		return viewName;
	}

}
