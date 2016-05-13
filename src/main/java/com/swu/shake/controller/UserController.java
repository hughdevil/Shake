package com.swu.shake.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.tools.internal.ws.processor.model.Request;
import com.swu.shake.log.UserLog;
import com.swu.shake.model.Item;
import com.swu.shake.model.Pager;
import com.swu.shake.model.Role;
import com.swu.shake.model.User;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.RoleService;
import com.swu.shake.service.UserService;
import com.swu.shake.util.FileUtility;
import com.swu.shake.util.MD5Util;
import com.swu.shake.util.MsgException;
import com.swu.shake.util.TimeUtil;

//标识为控制层
@Controller
public class UserController {
	private static final int AUTHORISE_SITER = 3;
	private static final int AUTHORISE_ADMIN = 4;
	private static final int PAGE_SIZE = 10;
	private MD5Util md5Util;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	private RoleService roleService;
	private ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	@Resource(name = "itemService")
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

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

	/**
	 * 返回的User少pwd
	 * 
	 * @param request
	 * @param session
	 * @param headpic
	 * @return
	 * @throws IOException
	 */
	public User getUser(HttpServletRequest request, HttpSession session, CommonsMultipartFile headpic) throws IOException {
		User user = new User();
		String uname = request.getParameter("uname");
		// 1
		user.setName(uname);
		String sexString = request.getParameter("sex");
		byte sex = 0;
		// 2
		if ("secret".equals(sexString)) {
			user.setSex((byte) 0);
		} else if ("male".equals(sexString)) {
			user.setSex((byte) 1);
		} else if ("female".equals(sexString)) {
			user.setSex((byte) -1);
		} else {
			// 如果没有勾选默认是保密
			user.setSex(sex);
		}

		// 3
		user.setPhone(request.getParameter("phone"));
		// 4
		user.setQQ(request.getParameter("qq"));
		// 5
		user.setAddr(request.getParameter("addr"));
		// 6
		user.setEmail(request.getParameter("mail") + "@" + request.getParameter("mailtype"));
		// 7
		user.setIP(request.getRemoteAddr());

		// 头像
		if (headpic != null && !headpic.getOriginalFilename().equals("")) {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
			logger.info("上传文件的名字：" + headpic.getOriginalFilename());
			/** 获取文件的后缀* */
			String suffix = headpic.getOriginalFilename().substring(headpic.getOriginalFilename().lastIndexOf("."));

			String curpath = session.getServletContext().getRealPath("/");
			Date uploadDate = new Date();
			String fileName = "upload/pic/" + dateformat.format(uploadDate) + MD5Util.getMD5(uploadDate.toString() + headpic.getOriginalFilename()) + suffix;
			String path = curpath + fileName;
			logger.info("path:" + path);
			FileUtility.saveUploadFile(headpic.getInputStream(), path);

			// 8
			user.setHeadpic(fileName);
		}

		return user;
	}

	/****************** 以下为 请求响应 ********************/
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session) throws Exception {
		String name = request.getParameter("name");
		String unPassword = request.getParameter("password");
		String password = MD5Util.getMD5(unPassword);
		User u = userService.login(name, password);
		if (null != u) {
			session.setAttribute("user", u);
			if (u.getRole() != null && u.getRole().getRlevel() >= AUTHORISE_ADMIN) {
				session.setAttribute("isADMIN", true);
			}
			logger.info(new UserLog(u.getUid(), u.getName(), u.getUid(), u.getName(), "LOGIN", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
			// return "redirect:/item/post.do";
			request.setAttribute("message", "登录成功,马上跳转到首页");
			request.setAttribute("jumpUri", "/item/post");
			return "/comm/successJ";
		} else {
			request.setAttribute("message", "登录失败，用户名不存在或密码不正确");
			logger.info(new UserLog(0, name, 0, name, "LOGIN", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			return "/comm/failure";
		}
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String register() {
		return "/user/register";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpSession session, @RequestParam(value = "hearpic", required = false) CommonsMultipartFile headpic)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		String upwd = request.getParameter("psw");
		String uname = request.getParameter("uname");

		String message = "";
		String jumpUri = "";
		String viewName = "";

		// 密码格式校验不用
		// 因为：本身密码就属于约束正常的前台用户，确保密码的复杂度
		// 对于恶意用户，绕过前台，就算限制也然并软
		if (null == upwd) {
			message = "密码为空，请重新注册。马上跳转到注册页面";
			jumpUri = "/user/register";
			viewName = "/comm/failureJ";
			logger.info(new UserLog(0, uname, 0, "", "REGISTER", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
		} else {
			try {
				User user = this.getUser(request, session, headpic);
				user.setPassword(md5Util.getMD5(upwd));
				user.setRegDate(new Date());
				User newUser = userService.register(user);
				logger.info(new UserLog(newUser.getUid(), uname, newUser.getUid(), uname, "REGISTER", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());

				message = "请登录";
				viewName = "/comm/success";

			} catch (MsgException e) {
				message = "用户名已经存在,请重新注册";
				jumpUri = "/user/register";
				viewName = "/comm/failureJ";

				logger.info(new UserLog(0, uname, 0, uname, "REGISTER", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
		}
		request.setAttribute("message", message);
		request.setAttribute("jumpUri", jumpUri);
		mav.setViewName(viewName);
		return mav;

	}

	@RequestMapping(value = "/user/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		String viewName = "";
		session.removeAttribute("user");
		session.removeAttribute("isADMIN");
		viewName = "/comm/success";
		request.setAttribute("message", "当前用户已登出");
		return viewName;
	}

	@RequestMapping(value = "/user/query", method = RequestMethod.GET)
	public String query(HttpSession session) {
		String viewName = "";
		viewName = "/user/query";
		return viewName;
	}

	@RequestMapping(value = "/user/query", method = RequestMethod.POST)
	public String query(HttpSession session, Model model) {
		String viewName = "";
		viewName = "/user/query";
		return viewName;
	}

	@RequestMapping(value = "/user/repwd", method = RequestMethod.GET)
	public String repwd(HttpSession session, Model model) {
		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else {
			viewName = "/user/repwd";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/user/repwd", method = RequestMethod.POST)
	public String repwd(HttpSession session, HttpServletRequest request) {
		String viewName = "";
		String message = "";
		String jumpUri = "";

		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else {
			String oldpwd = request.getParameter("oldpsw");
			String upwd = request.getParameter("psw");
			if (null == oldpwd || null == upwd) {
				session.removeAttribute("user");
				session.removeAttribute("isADMIN");

				message = "请重新登录";
				viewName = "/comm/failure";

				logger.warn(new UserLog(curuser.getUid(), "", curuser.getUid(), "", "repwd", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());

			} else if (md5Util.getMD5(oldpwd).equals(curuser.getPassword())) {
				curuser.setPassword(md5Util.getMD5(upwd));

				userService.modifyPwd(curuser);

				// 修改成功后要重新登录
				session.removeAttribute("user");
				session.removeAttribute("isADMIN");
				message = "请重新登录";
				viewName = "/comm/success";

				logger.info(new UserLog(curuser.getUid(), "", curuser.getUid(), "", "REPWD", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
			} else {
				message = "旧密码错误,请重试";
				jumpUri = "/user/repwd";
				viewName = "/comm/failureJ";

				logger.warn(new UserLog(curuser.getUid(), "", curuser.getUid(), "", "REPWD", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
		}
		request.setAttribute("jumpUri", jumpUri);
		request.setAttribute("message", message);
		return viewName;

	}

	@RequestMapping(value = "/user/{uid}/home", method = RequestMethod.GET)
	public String home(HttpSession session, @PathVariable(value = "uid") int uid, Model model) {
		int curpage = 1;
		Long count = itemService.getCountByUid(uid);
		Pager pager = new Pager(count, PAGE_SIZE, curpage);

		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		boolean candel = false;
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else {
			viewName = "/user/home";
			User showuser = this.userService.getUserById(uid);
			model.addAttribute("showuser", showuser);

			// 判断是否有删除选项
			Role role = showuser.getRole();
			if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_SITER) {
				if (role == null) {
					candel = true;
				} else {
					candel = curuser.getRole().getRlevel() > role.getRlevel() ? true : false;
				}
			} else {
				candel = false;
			}

			List<Item> items = itemService.getSomebodyItemsByPage(uid, pager.getStartRow(), PAGE_SIZE);
			model.addAttribute("pager", pager);
			model.addAttribute("uid", uid);
			model.addAttribute("items", items);

		}
		model.addAttribute("candel", candel);
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/user/{uid}/home", params = "page", method = RequestMethod.GET)
	public String home(HttpSession session, HttpServletRequest request, @PathVariable(value = "uid") int uid, Model model) {
		int curpage = Integer.parseInt(request.getParameter("page"));
		Long count = itemService.getCountByUid(uid);
		Pager pager = new Pager(count, PAGE_SIZE, curpage);

		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		boolean candel = false;
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else {
			viewName = "/user/home";
			User showuser = this.userService.getUserById(uid);
			model.addAttribute("showuser", showuser);

			// 判断是否有删除选项
			Role role = showuser.getRole();
			if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_SITER) {
				if (role == null) {
					candel = true;
				} else {
					candel = curuser.getRole().getRlevel() > role.getRlevel() ? true : false;
				}
			} else {
				candel = false;
			}

			List<Item> items = itemService.getSomebodyItemsByPage(uid, pager.getStartRow(), PAGE_SIZE);
			model.addAttribute("pager", pager);
			model.addAttribute("uid", uid);
			model.addAttribute("items", items);

		}
		model.addAttribute("candel", candel);
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/user/{uid}/edit", method = RequestMethod.GET)
	public String edit(HttpSession session, @PathVariable(value = "uid") int uid, Model model) {
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		boolean isCuruser = false;
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else if (uid == curuser.getUid()) {
			// 前台显示的是当前用户信息
			viewName = "/user/edit";
			// 重新查询是防止修改一次后再次修改，信息没有同步
			User user = userService.getUserById(uid);
			session.setAttribute("user", user);

			isCuruser = true;
			String email = user.getEmail();
			String[] strs = email.split("@");
			if (strs.length >= 2) {
				model.addAttribute("mail", strs[0]);
				model.addAttribute("mailtype", strs[1]);
			}
			model.addAttribute("upuser", user);

			// 判断有无必要查询这个用户资料
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_SITER) {
			// 前台显示的是从数据库里查询出来的USER
			User user = userService.getUserById(uid);
			Role role = user.getRole();
			// role == null是无角色，所以可以随便修改
			// role不为null时，并且当前用户角色等级大于被修改的才可以修改
			if (role == null || (role != null && role.getRlevel() < curuser.getRole().getRlevel())) {

				String email = user.getEmail();
				String[] strs = email.split("@");
				if (strs.length >= 2) {
					model.addAttribute("mail", strs[0]);
					model.addAttribute("mailtype", strs[1]);
				}

				// 此时还可已修改这个用户的等级
				List<Role> roles = roleService.getRoleByLevel(curuser.getRole().getRlevel());
				model.addAttribute("roles", roles);
				model.addAttribute("upuser", user);
				viewName = "/user/edit";

			} else {
				message = "您无权限修改该用户资料";
				viewName = "/comm/failureJ";
				jumpUri = "/user/" + uid + "/home";
			}
		} else {
			message = "请勿修改他人资料资料";
			viewName = "/comm/failureJ";
			jumpUri = "/user/" + uid + "/home";
		}
		model.addAttribute("iscuruser", isCuruser);
		model.addAttribute("message", message);
		model.addAttribute("jumpUri", jumpUri);
		return viewName;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpSession session, HttpServletRequest request, @RequestParam(value = "uid") int uid,
			@RequestParam(value = "hearpic", required = false) CommonsMultipartFile[] headpics) throws IOException {
		ModelAndView mav = new ModelAndView();
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		CommonsMultipartFile headpic = null;
		if (null != headpics) {
			for (CommonsMultipartFile hp : headpics)
				headpic = hp;
		}

		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else if (uid == curuser.getUid()) {
			String upwd = md5Util.getMD5(request.getParameter("psw"));
			if (curuser.getPassword().equals(upwd)) {

				User user = this.getUser(request, session, headpic);
				user.setUid(Integer.parseInt(request.getParameter("uid")));

				if (!userService.modify(user)) {
					message = "修改错误，请稍后再试";
					viewName = "/comm/failure";
				} else {
					viewName = "/comm/successJ";
					jumpUri = "/user/" + uid + "/home";
					message = "马上跳转到个人页面";
					logger.info(new UserLog(uid, "", uid, "", "EDIT", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
				}
			} else {
				message = "确认密码错误,马上返回修改页面";
				viewName = "/comm/failureJ";
				jumpUri = "/user/" + uid + "/edit";
				logger.info(new UserLog(uid, "", uid, "", "EDIT", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());

			}

			// 判断有无必要查询这个用户资料
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_SITER) {
			Role role = userService.getUserById(uid).getRole();
			// role == null是无角色，所以可以随便修改
			// role不为null时，并且当前用户角色等级大于被修改的才可以修改
			if (role == null || (role != null && role.getRlevel() < curuser.getRole().getRlevel())) {
				String rid = request.getParameter("rid");
				Role setter = roleService.getRole(rid);
				User user = this.getUser(request, session, headpic);
				user.setUid(Integer.parseInt(request.getParameter("uid")));

				user.setRole(setter);
				if (!userService.modify(user)) {
					message = "修改错误，请稍后再试";
					viewName = "/comm/failure";
				} else {
					viewName = "/comm/successJ";
					jumpUri = "/user/" + uid + "/home";
					message = "马上跳转到个人页面";
					logger.info(new UserLog(curuser.getUid(), "", user.getUid(), "", "EDIT", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
				}

			} else {
				message = "您无权限修改该用户资料";
				viewName = "/comm/failureJ";
				jumpUri = "/user/" + uid + "/home";
				logger.warn(new UserLog(curuser.getUid(), "", uid, "", "EDIT", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}

		} else {
			message = "请勿修改他人资料";
			jumpUri = "/user/" + uid + "/home";
			viewName = "/comm/failureJ";
		}
		mav.addObject("jumpUri", jumpUri);
		mav.addObject("message", message);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/user/{uid}/del", method = RequestMethod.GET)
	public String del(HttpSession session, @PathVariable(value = "uid") int uid, Model model) {
		String viewName = "";
		String message = "";
		String jumpUri = "";
		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录，请先登录";
			viewName = "/comm/failure";
		} else {
			Role role = userService.getUserById(uid).getRole();
			if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_SITER) {
				if (role == null || (role != null && curuser.getRole().getRlevel() > role.getRlevel())) {
					try {
						userService.remove(uid);
						viewName = "/comm/success";
						logger.info(new UserLog(curuser.getUid(), "", uid, "", "del", UserLog.SUCCESS, "", TimeUtil.getUSDate()).toString());
					} catch (Exception e) {
						message = "系统出错，我们可爱的程序猿正在积极努力的调试中";
						viewName = "/comm/failure";
						logger.error(message, e);
					}
				} else {
					message = "权限小于对方，不能删除";
					viewName = "/comm/failureJ";
					jumpUri = "/user/" + uid + "/home";
					logger.warn(new UserLog(curuser.getUid(), "", uid, "", "DEL", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
				}
			} else {
				message = "非站务，不能删除";
				viewName = "/comm/failureJ";
				jumpUri = "/user/" + uid + "/home";
				logger.warn(new UserLog(curuser.getUid(), "", uid, "", "DEL", UserLog.FAILLURE, "", TimeUtil.getUSDate()).toString());
			}
		}

		model.addAttribute("jumpUri", jumpUri);
		model.addAttribute("message", message);
		return viewName;
	}

}
