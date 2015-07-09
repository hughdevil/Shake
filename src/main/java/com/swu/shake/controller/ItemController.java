package com.swu.shake.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.swu.shake.model.Item;
import com.swu.shake.model.ItemImage;
import com.swu.shake.model.ItemType;
import com.swu.shake.model.Pager;
import com.swu.shake.model.User;
import com.swu.shake.service.ItemService;
import com.swu.shake.service.ItemTypeService;
import com.swu.shake.util.FileUtility;
import com.swu.shake.util.MD5Util;
import com.swu.shake.util.MsgException;

//标识为控制层
@Controller
@RequestMapping("/item")
public class ItemController {
	private static final int PAGE_SIZE = 12;
	private static final Logger logger = LoggerFactory
			.getLogger(ItemController.class);
	private static final int AUTHORISE_MODERATOR = 2;

	private ItemService itemService;
	private ItemTypeService itemTypeService;

	public ItemService getItemService() {
		return itemService;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ItemTypeService getItemTypeService() {
		return itemTypeService;
	}

	@Autowired
	public void setItemTypeService(ItemTypeService itemTypeService) {
		this.itemTypeService = itemTypeService;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(HttpSession session, ModelMap mm) {
		Long count = itemService.getCount();
		Pager pager = new Pager(count, PAGE_SIZE, 1);
		mm.addAttribute(
				"itemList",
				itemService.getItems(pager.getStartRow(), pager.getStartRow()
						+ PAGE_SIZE));

		List<ItemType> itemtypes = itemTypeService.getItemTypes();
		mm.addAttribute("itemtypes", itemtypes);
		mm.addAttribute("pager", pager);

		// 分类移除
		session.removeAttribute("tid");
		// 模糊移除
		session.removeAttribute("iname");
		return "item/post";
	}

	@RequestMapping(value = "/post", params = "type", method = RequestMethod.GET)
	public String postByTid(HttpServletRequest request, HttpSession session,
			ModelMap mm) {
		int curtid = Integer.parseInt(request.getParameter("type"));
		Long count = itemService.getCount(curtid);
		Pager pager = new Pager(count, PAGE_SIZE, 1);
		mm.addAttribute(
				"itemList",
				itemService.getItems(curtid, pager.getStartRow(),
						pager.getStartRow() + PAGE_SIZE));

		List<ItemType> itemtypes = itemTypeService.getItemTypes();
		mm.addAttribute("itemtypes", itemtypes);
		mm.addAttribute("pager", pager);
		session.setAttribute("tid", curtid);

		// 移除模糊查找影响
		session.removeAttribute("iname");
		return "item/post";
	}

	@RequestMapping(value = "/post", params = "page", method = RequestMethod.GET)
	public String post(HttpServletRequest request, HttpSession session,
			ModelMap mm) {
		int curpage = Integer.parseInt(request.getParameter("page"));
		Long count = null;
		Pager pager = null;
		if (null != session.getAttribute("tid")) {
			int curtid = (Integer) session.getAttribute("tid");
			count = itemService.getCount(curtid);
			pager = new Pager(count, PAGE_SIZE, curpage);
			mm.addAttribute("itemList", itemService.getItems(curtid,
					pager.getStartRow(), PAGE_SIZE));

		} else if (null != session.getAttribute("iname")) {
			String iname = (String) session.getAttribute("iname");
			count = itemService.getCount(iname);
			pager = new Pager(count, PAGE_SIZE, curpage);
			mm.addAttribute("itemList", itemService.getItemsByName(iname,
					pager.getStartRow(), PAGE_SIZE));

			List<ItemType> itemtypes = itemTypeService.getItemTypes();
			mm.addAttribute("itemtypes", itemtypes);
			mm.addAttribute("pager", pager);

			// 移除分类影响
			session.removeAttribute("tid");
		} else {
			count = itemService.getCount();
			pager = new Pager(count, PAGE_SIZE, curpage);
			mm.addAttribute("itemList",
					itemService.getItems(pager.getStartRow(), PAGE_SIZE));
		}
		mm.addAttribute("pager", pager);

		List<ItemType> itemtypes = itemTypeService.getItemTypes();
		mm.addAttribute("itemtypes", itemtypes);
		return "item/post";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish(HttpSession session, Model model) {
		User curuser = (User) session.getAttribute("user");
		String viewName = "";
		String message = "";
		if (curuser == null) {
			viewName = "comm/failure";
			message = "请先登录";
		} else {
			viewName = "item/publish";
			List<ItemType> itemtypes = itemTypeService.getItemTypes();
			model.addAttribute("itemtypes", itemtypes);

		}
		model.addAttribute("message", message);
		return viewName;
	}

	/**
	 * 发布商品
	 * 
	 * @param mFiles
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ModelAndView publish(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "itemtype", required = false) int tid,
			@RequestParam(value = "itemImages", required = false) CommonsMultipartFile[] mFiles,
			@RequestParam(value = "postImage", required = false) CommonsMultipartFile[] postFile) {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
		ModelAndView mav = new ModelAndView();
		User curUser = (User) session.getAttribute("user");
		String postImage = null;
		if (null != curUser) {
			try {
				Set<ItemImage> itemImages = new HashSet<ItemImage>();
				// 封面照片
				if (postFile != null) {
					for (CommonsMultipartFile mFile : postFile) {
						if (!mFile.isEmpty()) {
							postImage = upload(session, dateformat, mFile)
									.getIiname();
						}
					}
				}
				// 其他照片
				if (mFiles != null) {
					for (CommonsMultipartFile mFile : mFiles) {
						if (!mFile.isEmpty()) {
							itemImages.add(upload(session, dateformat, mFile));
						}
					}
				}

				Item item = new Item();
				item.setIname(request.getParameter("title"));
				item.setiNumber(Integer.parseInt(request.getParameter("number")));
				item.setIprice(Double.parseDouble(request.getParameter("price")));
				item.setIdesc(request.getParameter("desc"));
				item.setNewly(Integer.parseInt(request.getParameter("newly")));
				item.setHasdate(request.getParameter("hasdate"));
				item.setValid(Boolean.parseBoolean(request
						.getParameter("isvalid")));

				ItemType itemtype = itemTypeService.getItemTypeById(tid);
				item.setItemtype(itemtype);

				item.setOnshelfdate(new Date());
				item.setUser(curUser);
				item.setPostImage(postImage);
				item.setItemImages(itemImages);
				Item rei = itemService.register(item);
				mav.addObject("item", rei);
				mav.setViewName("comm/success");
			} catch (MsgException e) {
				request.setAttribute("message", e);
				mav.setViewName("comm/failure");
			} catch (IOException e) {
				e.printStackTrace();
				mav.setViewName("comm/failure");
			}
		} else {
			request.setAttribute("message", "未登录，亲！！");
			mav.setViewName("comm/failure");
		}
		return mav;
	}

	/**
	 * 上传单张图片
	 * 
	 * @param session
	 * @param dateformat
	 * @param mFile
	 * @return
	 * @throws IOException
	 */
	public ItemImage upload(HttpSession session, SimpleDateFormat dateformat,
			CommonsMultipartFile mFile) throws IOException {
		logger.info("上传文件的名字：" + mFile.getOriginalFilename());
		/** 获取文件的后缀* */
		String suffix = mFile.getOriginalFilename().substring(
				mFile.getOriginalFilename().lastIndexOf("."));

		String curpath = session.getServletContext().getRealPath("/");
		Date uploadDate = new Date();
		String fileName = "upload/pic/"
				+ dateformat.format(uploadDate)
				+ MD5Util.getMD5(uploadDate.toString()
						+ mFile.getOriginalFilename()) + suffix;
		String path = curpath + fileName;
		logger.info("path:" + path);
		FileUtility.saveUploadFile(mFile.getInputStream(), path);
		ItemImage ii = new ItemImage();

		ii.setIiname(fileName);
		ii.setUploadDate(uploadDate);
		logger.info("=======文件上传成功=======");
		return ii;
	}

	@RequestMapping(value = "/{iid}/detail", method = RequestMethod.GET)
	public String showDetail(@PathVariable(value = "iid") int iid, Model model,
			HttpServletRequest request, HttpSession session) {
		String viewName = "";
		Item item = this.itemService.getDetail(iid);
		if (null == item) {
			viewName = "comm/failure";
			request.setAttribute("message", "童鞋，你确定有这个编号？");
		} else {
			viewName = "item/detail";
			model.addAttribute("item", item);
		}
		return viewName;
	}

	@RequestMapping(value = "/{iid}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "iid") int iid,
			HttpSession session, Model model) {
		String viewName = "";
		String message = "";
		User curuser = (User) session.getAttribute("user");
		Item item = null;
		if (null == curuser) {
			message = "未登录";
			viewName = "comm/failure";
		} else if (null == (item = itemService.getItem(iid))) {
			message = "商品不存在或已经删除";
			viewName = "comm/failure";
		} else if (item.getUser().getUid() == curuser.getUid()
				|| (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR)) {

			boolean flag = false;
			if (item.getUser().getUid() == curuser.getUid()) {
				flag = true;
			} else if (item.getUser().getRole() == null) {
				flag = true;
			} else if (curuser.getRole().getRlevel() > item.getUser().getRole()
					.getRlevel()) {
				flag = true;
			} else {
				message = "没有权限执行此操作，请查看对方的角色等级";
				viewName = "/comm/failure";
			}
			if (flag) {
				viewName = "item/edit";
				List<ItemType> itemtypes = itemTypeService.getItemTypes();
				List<ItemImage> itemimages = itemService.getImgs(item.getIid());

				model.addAttribute("item", item);
				model.addAttribute("itemtypes", itemtypes);
				model.addAttribute("itemimages", itemimages);
			}
		} else {
			message = "该商品不是您的";
			viewName = "comm/failure";
		}
		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(
			@RequestParam("iid") int iid,
			@RequestParam("itemtype") int tid,
			@RequestParam(value = "selectedimages", required = false) int[] iiids,
			@RequestParam("postImage") CommonsMultipartFile[] postFile,
			@RequestParam(value = "newimages", required = false) CommonsMultipartFile[] mFiles,
			HttpSession session, HttpServletRequest request, Model model)
			throws IOException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
		String viewName = "";
		String message = "";
		Item item = null;
		User curuser = (User) session.getAttribute("user");

		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (null == (item = itemService.getItem(iid))) {
			message = "商品不存在或已经删除";
			viewName = "/comm/failure";
		} else if (item.getUser().getUid() == curuser.getUid()
				|| (curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR)) {

			boolean flag = false;
			if (item.getUser().getUid() == curuser.getUid()) {
				flag = true;
			} else if (item.getUser().getRole() == null) {
				flag = true;
			} else if (curuser.getRole().getRlevel() > item.getUser().getRole()
					.getRlevel()) {
				flag = true;
			} else {
				message = "没有权限执行此操作，请查看对方的角色等级";
				viewName = "/comm/failure";
			}
			if (flag) {

				// 检查原封面存不存在
				String postImage = item.getPostImage();
				String fileurl = ItemController.class.getResource("/")
						.getPath();
				fileurl = fileurl.split("WEB-INF/classes/")[0] + postImage;
				File file = new File(fileurl);
				if (!file.exists()) {
					postImage = null;
					logger.info(fileurl);
					logger.info("原先的封面不存在！！");
				}
				// 如果有新的封面，直接替换
				if (postFile != null) {
					for (CommonsMultipartFile mFile : postFile) {
						if (!mFile.isEmpty()) {
							postImage = upload(session, dateformat, mFile)
									.getIiname();
						}
					}
				}

				List<ItemImage> imageList = itemService.getImgs(item.getIid());
				List<ItemImage> removeList = new ArrayList<ItemImage>();
				// 剔除已经不需要的图片 步骤1
				if (iiids != null) {
					for (ItemImage ii : imageList) {
						boolean temp = false;
						for (int iiid : iiids) {
							if (ii.getIiid() == iiid)
								temp = true;
						}
						if (!temp) {
							removeList.add(ii);
						}
					}
					imageList.removeAll(removeList);
				} else {
					imageList.clear();
				}
				Set<ItemImage> itemImages = new HashSet<ItemImage>(imageList);

				// 添加新上传的图片
				if (mFiles != null) {
					for (CommonsMultipartFile mFile : mFiles) {
						if (!mFile.isEmpty()) {
							itemImages.add(upload(session, dateformat, mFile));
						}
					}
				}

				item.setIname(request.getParameter("title"));
				item.setiNumber(Integer.parseInt(request.getParameter("number")));
				item.setIprice(Double.parseDouble(request.getParameter("price")));
				item.setIdesc(request.getParameter("desc"));
				item.setValid(Boolean.parseBoolean(request
						.getParameter("isvalid")));

				ItemType itemtype = itemTypeService.getItemTypeById(tid);
				item.setItemtype(itemtype);

				// 图片
				item.setPostImage(postImage);
				item.setItemImages(itemImages);

				viewName = itemService.modify(item) ? "/comm/success"
						: "/comm/failure";

				// 剔除已经不需要的图片 步骤2
				itemService.clearUnuserfulImg();

			}
		} else {
			message = "该商品不是您的";
			viewName = "comm/failure";
		}

		model.addAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/{iid}/unload", method = RequestMethod.GET)
	public String unload(@PathVariable(value = "iid") int iid,
			HttpServletRequest request, HttpSession session) {
		String viewName = "";
		String message = "";
		Item item = null;

		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else if (null == (item = itemService.getItem(iid))) {
			message = "商品不存在或已经删除";
			viewName = "/comm/failure";
		} else if (item.getUser().getUid() == curuser.getUid()
				|| ((curuser.getRole() != null && curuser.getRole().getRlevel() >= AUTHORISE_MODERATOR))) {
			// 还需要判断删除商品的发布人是不是自己的下级
			boolean flag = false;
			if (item.getUser().getUid() == curuser.getUid()) {
				flag = true;
			} else if (item.getUser().getRole() == null) {
				flag = true;
			} else if (curuser.getRole().getRlevel() > item.getUser().getRole()
					.getRlevel()) {
				flag = true;
			} else {
				message = "没有权限执行此操作，请查看对方的角色等级";
				viewName = "/comm/failure";
			}
			if (flag) {
				try {
					itemService.remove(iid);
					viewName = "/comm/success";
				} catch (Exception e) {
					message = "删除失败";
					viewName = "/comm/failure";
				}
			}

		} else {
			message = "该商品不是您的";
			viewName = "comm/failure";
		}

		request.setAttribute("message", message);
		return viewName;
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String query(HttpServletRequest request, HttpSession session,
			Model model) {
		String viewName = "";
		String message = "";

		User curuser = (User) session.getAttribute("user");
		if (null == curuser) {
			message = "未登录";
			viewName = "/comm/failure";
		} else {
			String iname = request.getParameter("iname");
			Long count = itemService.getCount(iname);
			Pager pager = new Pager(count, PAGE_SIZE, 1);
			model.addAttribute("itemList", itemService.getItemsByName(iname,
					pager.getStartRow(), PAGE_SIZE));

			List<ItemType> itemtypes = itemTypeService.getItemTypes();
			model.addAttribute("itemtypes", itemtypes);
			model.addAttribute("pager", pager);
			session.setAttribute("iname", iname);
			viewName = "item/post";

			// 移除分类影响
			session.removeAttribute("tid");
		}
		request.setAttribute("message", message);
		return viewName;
	}
}
