package com.ntc.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.content.service.ContentService;
import com.ntc.mall.pojo.TbContent;

/**
 * 处理内容表相关
 * @author Michael-Chen
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 保存内容信息
	 * @param tbContent
	 * @return
	 */
	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public MallResult saveContent(TbContent tbContent){
		return contentService.saveContent(tbContent);
	}
}
