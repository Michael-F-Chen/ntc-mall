package com.ntc.mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntc.mall.common.pojo.SearchResult;
import com.ntc.mall.search.service.SearchService;

/**
 * 搜索页面
 * @author Michael-Chen
 */
@Controller
public class SearchController {

	@Value("${ITEM_ROWS}")
	private Integer ITEM_ROWS;
	@Autowired
	private SearchService searchService;
	
	/**
	 * 根据条件搜索商品的数据
	 * @param page
	 * @param qString
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/search")
	public String search(Integer page, @RequestParam(value = "q") String queryString, Model model) throws Exception {
			
		// 处理乱码
		queryString = new String(queryString.getBytes("iso-8859-1"),"Utf-8");
		
		SearchResult search = searchService.search(queryString, page, ITEM_ROWS);
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", search.getPageCount());	// 总页数
		model.addAttribute("itemList", search.getItemList());
		model.addAttribute("page", page);
		return "search";
	}
}
