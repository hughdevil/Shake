package com.swu.shake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1() {
		System.out.println("test1");
	}

	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	public void test2() {
		System.out.println("test2");
	}

	@RequestMapping("/test3")
	public String test3() {
		System.out.println("test3");
		return "redirect:/test/test1";
	}

	@RequestMapping(value = "/test4")
	public void test4() {
		System.out.println("test4");
	}
}
