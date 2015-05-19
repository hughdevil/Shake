package com.swu.shake.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
import com.swu.shake.model.Pager;
import com.swu.shake.model.User;
import com.swu.shake.service.ItemService;
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

	private ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(ModelMap mm) {
		Long count = itemService.getCount();
		Pager pager = new Pager(count, PAGE_SIZE, 1);
		mm.addAttribute(
				"itemList",
				itemService.getItems(pager.getStartRow(), pager.getStartRow()
						+ PAGE_SIZE));
		mm.addAttribute("pager", pager);
		return "item/post";
	}

	@RequestMapping(value = "/post", params = "page", method = RequestMethod.GET)
	public String postDo(HttpServletRequest request, HttpSession session,
			ModelMap mm) {
		int curpage = Integer.parseInt(request.getParameter("page"));
		Long count = itemService.getCount();
		Pager pager = new Pager(count, PAGE_SIZE, curpage);
		mm.addAttribute(
				"itemList",
				itemService.getItems(pager.getStartRow(), pager.getStartRow()
						+ PAGE_SIZE));
		mm.addAttribute("pager", pager);
		return "item/post";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish() {
		return "item/publish";
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
			@RequestParam("itemImages") CommonsMultipartFile[] mFiles,
			HttpServletRequest request, HttpSession session) {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
		ModelAndView mav = new ModelAndView();
		User curUser = (User) session.getAttribute("user");
		String postImage = null;
		if (null != curUser) {
			try {
				Set<ItemImage> itemImages = new HashSet<ItemImage>();
				if (mFiles != null) {
					for (CommonsMultipartFile mFile : mFiles) {
						if (!mFile.isEmpty()) {
							logger.info("上传文件的名字："
									+ mFile.getOriginalFilename());
							/** 获取文件的后缀* */
							String suffix = mFile.getOriginalFilename()
									.substring(
											mFile.getOriginalFilename()
													.lastIndexOf("."));

							String curpath = session.getServletContext()
									.getRealPath("/");
							Date uploadDate = new Date();
							String fileName = "upload/pic/"
									+ dateformat.format(uploadDate)
									+ MD5Util.getMD5(uploadDate.toString()
											+ mFile.getOriginalFilename())
									+ suffix;
							String path = curpath + fileName;
							logger.info("path:" + path);
							FileUtility.saveUploadFile(mFile.getInputStream(),
									path);
							ItemImage ii = new ItemImage();

							ii.setIiname(fileName);
							ii.setUploadDate(uploadDate);
							itemImages.add(ii);
							if (null == postImage)
								postImage = fileName;
							logger.info("=======文件上传成功=======");
						}
					}
				}

				Item item = new Item();
				item.setIname(request.getParameter("title"));
				item.setiNumber(Integer.parseInt(request.getParameter("number")));
				item.setIprice(Double.parseDouble(request.getParameter("price")));
				item.setIdesc(request.getParameter("desc"));
				item.setValid(Boolean.parseBoolean(request
						.getParameter("isvalid")));

				item.setOnshelfdate(new Date());
				item.setUser(curUser);
				item.setPostImage(postImage);
				item.setItemImages(itemImages);
				Item rei = itemService.register(item);
				mav.addObject("item", rei);
				mav.setViewName("comm/success");
			} catch (MsgException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", e);
				mav.setViewName("comm/failure");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mav.setViewName("comm/failure");
			}
		} else {
			request.setAttribute("message", "未登录，亲！！");
			mav.setViewName("comm/failure");
		}
		return mav;
	}

	@RequestMapping(value = "/{iid}/detail", method = RequestMethod.GET)
	public String showDetail(@PathVariable(value = "iid") Integer iid,
			Model model, HttpServletRequest request, HttpSession session) {
		String viewName = "";
		Item item = this.itemService.getItem(iid);
		if (null == item) {
			viewName = "comm/failure";
			request.setAttribute("message", "童鞋，你确定有这个编号？");
		} else {
			viewName = "item/detail";
			model.addAttribute("item", item);
		}
		return viewName;
	}
}
