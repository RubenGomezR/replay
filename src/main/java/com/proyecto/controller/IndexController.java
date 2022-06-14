package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	//URL principal
	@RequestMapping("")
	public String index() {
		return "redirect:/Home";
	}
}
