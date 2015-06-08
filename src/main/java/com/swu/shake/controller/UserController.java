package com.swu.shake.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.swu.shake.model.Item;
import com.swu.shake.model.Role;
import com.swu.shake.model.User;
import com.swu.shake.service.RoleService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MD5Util;
import com.swu.shake.util.MsgException;

//标识为控制层
@Controller
public class UserController {
	private UserService userService;
	private RoleService roleService;
	private MD5Util md5Util;

	public UserService getUserService() {
		return userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource(name = "roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	// 注入userservice
	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource(name = "md5Util")
	public void setMd5Util(MD5Util md5Util) {
		this.md5Util = md5Util;
	}

	public MD5Util getMd5Util() {
		return md5Util;
	}

	// @RequestMapping(value = "/user/login", method = RequestMethod.GET)
	// public ModelAndView userLogin(HttpServletRequest request,
	// HttpSession session) throws Exception {
	// ModelAndView mav = new ModelAndView();// 新建视图
	// mav.setViewName("/user/login");
	// mav.addObject("user", new User());
	// return mav;
	// }
	//
	// @RequestMapping(value = "/user/login", method = RequestMethod.POST)
	// public @ResponseBody User userLoginDo(HttpServletRequest request,
	// HttpSession session, @RequestBody User user) throws Exception {
	// String name = user.getName();
	// String unPassword = user.getPassword();
	// String password = MD5Util.getMD5(unPassword);
	// User u = userService.login(name, password);
	// if (null != u) {
	// session.setAttribute("user", u);
	// }
	// return u;
	// }

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session)
			throws Exception {
		String name = request.getParameter("name");
		String unPassword = request.getParameter("password");
		String password = MD5Util.getMD5(unPassword);
		User u = userService.login(name, password);
		if (null != u) {
			session.setAttribute("user", u);
		}
		return "redirect:/item/post.do";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String register() {
		return "/user/register";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("psw").equals(
				request.getParameter("repsw")) ? md5Util.getMD5(request
				.getParameter("psw")) : null;
		if (null == upwd) {
			request.setAttribute("message", "两次密码不一致");
			mav.setViewName("/comm/failure");
		} else {
			User user = new User();
			user.setName(uname);
			user.setPassword(upwd);
			String sexString = request.getParameter("sex");
			byte sex = 0;
			if ("secret".equals(sexString)) {
				user.setSex((byte) 0);
			} else if ("male".equals(sexString)) {
				user.setSex((byte) -1);
			} else if ("female".equals(sexString)) {
				user.setSex((byte) 1);
			} else {
				// 如果没有勾选默认是保密
				user.setSex(sex);
			}
			user.setIP(request.getRemoteAddr());
			user.setRegDate(new Date());
			user.setQQ(request.getParameter("qq"));
			user.setAddr(request.getParameter("addr"));
			user.setEmail(request.getParameter("mail") + "@"
					+ request.getParameter("mailtype"));
			user.setPhone(request.getParameter("phone"));
			try {
				userService.register(user);
			} catch (MsgException e) {
				request.setAttribute("message", "用户名已经存在");
				mav.setViewName("/comm/failure");
				return mav;
			}
			mav.setViewName("/comm/success");
		}
		return mav;
	}

	@RequestMapping(value = "/user/logout")
	public String logout(HttpSession session) {
		String viewName = "";
		session.removeAttribute("user");
		viewName = "redirect:/item/post.do";
		return viewName;
	}
}
