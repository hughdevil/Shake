package com.swu.shake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
public class HelpController {
	@RequestMapping(value = "")
	public String help() {
		return "help/introduce";

	}

	@RequestMapping(value = "/introduce")
	public String introduce() {
		return "help/introduce";

	}

	@RequestMapping(value = "/roleauth")
	public String roleauth() {
		return "help/roleauth";

	}

	@RequestMapping(value = "/demo")
	public String demo() {
		return "help/demo";

	}

	@RequestMapping(value = "/install")
	public String install() {
		return "help/install";

	}
}
