package com.ntc.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示页面
 * @author Michael-Chen
 */
@Controller
public class PagController {
	
	@RequestMapping("/")
	public String showIndex(){		
		return "index";
	}
	
	// 显示商品查询的页面
	@RequestMapping("/{page}")
	public String showPage(@PathVariable(value="page") String Page){
		return Page;
	}
}
