package com.ntc.mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 * @author Michael-Chen
 */
@Controller
public class PageController {

	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
	
}
