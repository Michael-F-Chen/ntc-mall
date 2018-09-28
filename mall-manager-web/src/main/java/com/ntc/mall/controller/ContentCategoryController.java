package com.ntc.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntc.mall.common.pojo.EasyUITreeNode;
import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.content.service.ContentCategoryService;

/**
 * 内容分类的处理
 * @author Michael-Chen
 */
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * url:'/content/category/list',
	 * animate : true,
	 * method : 'GET',
	 * 参数 : id
	 * @param Page
	 * @return
	 */
	@RequestMapping(value="/content/category/list", method=RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		// 需要的参数时id,第一次不会传递id,需要设置默认参数,0代表顶层节点
		return contentCategoryService.getContentCategoruList(parentId);
	}
	
	/**
	 * 添加节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public MallResult createContentCategory(Long parentId, String name){
		return contentCategoryService.createContentCategory(parentId, name);
	}
	
	/**
	 * 添加节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/delete", method=RequestMethod.POST)
	@ResponseBody
	public MallResult deleteContentCategory(Long id){
		return contentCategoryService.deleteContentCategory(id);
	}
}
