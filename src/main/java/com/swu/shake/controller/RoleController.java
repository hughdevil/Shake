package com.swu.shake.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
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

			List<Role> roles = roleService.finall();
			model.addAttribute("roles", roles);

			viewName = "role/add";
		} else {
			viewName = "comm/failure";
			message = "权限不够";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "rlevelcode", required = true) String rlevelcode,
			HttpServletRequest request, HttpSession session, Model model, Role role) {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session, Model model) {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else {
			viewName = "/role/list";
			List<Role> roles = roleService.finall();
			model.addAttribute("roles", roles);
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{rid}/del", method = RequestMethod.GET)
	public String del(HttpSession session, Model model, @PathVariable(value = "rid") String rid) {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			Role role = roleService.getRole(rid);
			if (role != null && curuser.getRole().getRlevel() > role.getRlevel()) {
				if (roleService.delete(rid)) {
					viewName = "/comm/success";
				} else {
					message = "系统异常，稍后再试";
					viewName = "/comm/failure";
				}

			} else {
				message = "角色权限等级不足";
				viewName = "/comm/failure";
			}

		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{rid}/edit", method = RequestMethod.GET)
	public String edit(HttpSession session, Model model, @PathVariable(value = "rid") String rid)
			throws UnsupportedEncodingException {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			Role role = roleService.getRole(rid);
			if (role != null && curuser.getRole().getRlevel() > role.getRlevel()) {

				int rlevel = curuser.getRole().getRlevel();
				Map<String, String> rlevels = null;
				try {
					rlevels = RlevelUtil.getAbleLevel(rlevel);
				} catch (UnsupportedEncodingException e) {
					model.addAttribute("message", "配置文件编码出错");
					e.printStackTrace();
				}
				model.addAttribute("rlevels", rlevels);
				model.addAttribute("rleveldesc", RlevelUtil.getDesc(role.getRlevel()));
				model.addAttribute("role", role);

				List<Role> roles = roleService.finall();
				model.addAttribute("roles", roles);

				viewName = "/role/edit";

			} else {
				message = "角色权限等级不足";
				viewName = "/comm/failure";
			}
		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "rlevelcode", required = true) String rlevelcode) {
		String viewName = "";
		User curuser = (User) session.getAttribute("user");
		String message = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_ADMIN) {
			String rid = request.getParameter("rid");
			Role role = roleService.getRole(rid);
			if (role != null && curuser.getRole().getRlevel() > role.getRlevel()) {
				Role newRole = new Role();
				int rlevel = RlevelUtil.getLevel(rlevelcode);
				newRole.setRid(rid);
				newRole.setRlevel(rlevel);
				newRole.setRname(request.getParameter("rname"));
				newRole.setRdesc(request.getParameter("rdesc"));

				if (roleService.modify(newRole))
					viewName = "/comm/success";
				else {
					message = "系统异常，稍后再试";
					viewName = "/comm/failure";
				}

			} else {
				message = "角色权限等级不足";
				viewName = "/comm/failure";
			}
		} else {
			message = "权限不足";
			viewName = "/comm/failure";
		}
		request.setAttribute("message", message);
		return viewName;
	}
}
