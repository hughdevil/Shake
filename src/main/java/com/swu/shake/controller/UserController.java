package com.swu.shake.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.swu.shake.model.User;
import com.swu.shake.service.UserService;
import com.swu.shake.util.MD5Util;
import com.swu.shake.util.MsgException;

//标识为控制层
@Controller
public class UserController {
	private UserService userService;
	private MD5Util md5Util;

	public UserService getUserService() {
		return userService;
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

	// 加to来访问到jsp页面，不允许直接访问jsp
	@RequestMapping("/user/tologin")
	public ModelAndView userLogin(HttpServletRequest request,
			HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();// 新建视图
		mav.setViewName("/user/login");
		return mav;
	}

	@RequestMapping("/user/login")
	public ModelAndView userLoginDo(HttpServletRequest request,
			HttpSession session) throws Exception {
		String name = request.getParameter("uname");
		String unPassword = request.getParameter("psw");
		String password = MD5Util.getMD5(unPassword);
		ModelAndView mav = new ModelAndView();// 新建视图
		try {
			User user = userService.login(name, password);
			if (null != user) {
				session.setAttribute("user", user);
				mav.setViewName("/comm/success");// 返回到success.jsp中
				mav.addObject("user", user);
			} else {
				mav.setViewName("/user/login");
			}

		} catch (IndexOutOfBoundsException e) {
			mav.setViewName("index");
		}
		return mav;
	}

	@RequestMapping("/user/toregister")
	public String userRegister(HttpServletRequest quest, HttpSession session)
			throws Exception {
		return "/user/register";
	}

	@RequestMapping("/user/register")
	public ModelAndView userRegisterDo(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("psw").equals(
				request.getParameter("repsw")) ? md5Util.getMD5(request
				.getParameter("pwd")) : null;
		if (null == upwd) {
			request.setAttribute("message", "两次密码不一致");
			mav.setViewName("/user/register.jsp");
		} else if (null == userService.login(uname, upwd)) {
			request.setAttribute("message", "用户名已经存在");
			mav.setViewName("/user/register.jsp");
		} else {
			User user = new User();
			user.setName(uname);
			user.setPassword(upwd);
			String sexString = request.getParameter("sex");
			Boolean sex = null;
			if ("secret".equals(sexString)) {
			} else if ("male".equals(sexString)) {
				user.setSex(true);
			} else if ("female".equals(sexString)) {
				user.setSex(false);
			} else {
				// 如果没有勾选默认是保密
				user.setSex(sex);
			}
			user.setRegDate(new Date());
			try {
				userService.register(user);
			} catch (MsgException e) {
				e.printStackTrace();
			}
			mav.setViewName("/user/tologin");
		}
		return mav;
	}
}
