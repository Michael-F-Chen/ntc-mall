package com.ntc.mall.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ntc.mall.common.util.JsonUtils;
import com.ntc.mall.content.service.ContentService;
import com.ntc.mall.pojo.TbContent;
import com.ntc.mall.portal.pojo.Ad1Node;

/**
 * 展示首页
 * @author Michael-Chen
 */
@Controller
public class PageController {

	@Autowired
	private ContentService contentService;
	
	@Value("${AD1_CATEGORY_ID}")
	private Long categoryId;
	
	@Value("${AD1_HEIGHT}")
	private String AD1_HEIGHT;
	
	@Value("${AD1_HEIGHT_B}")
	private String AD1_HEIGHT_B;
	
	@Value("${AD1_WIDTH}")
	private String AD1_WIDTH;
	
	@Value("${AD1_WIDTH_B}")
	private String AD1_WIDTH_B;
	
	@RequestMapping("/index")
	public String showIndex(Model mode){
		// 添加业务逻辑，更具内容分类的id 查询内容列表
		List<TbContent> contentList = contentService.getContentListByCategoryId(categoryId);
		// 转换成自定的POJO列表
		List<Ad1Node> nodes = new ArrayList<>();
		for(TbContent content : contentList){
			Ad1Node node = new Ad1Node();
			node.setAlt(content.getSubTitle());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT);
			node.setHref(content.getUrl());
			node.setSrc(content.getPic());
			node.setSrcB(content.getPic2());
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_HEIGHT_B);
			nodes.add(node);
		}
		// 传递给jsp
		mode.addAttribute("ad1", JsonUtils.objectToJson(nodes));
		return "index";
	}
	
}
