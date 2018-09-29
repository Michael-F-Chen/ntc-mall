package com.ntc.mall.content.service;

import java.util.List;
import java.util.Locale.Category;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.pojo.TbContent;

/**
 * 内容处理接口
 * @author Michael-Chen
 */
public interface ContentService {

	/**
	 * 插入内容表
	 * @param content
	 * @return
	 */
	public MallResult saveContent(TbContent content);
	
	/**
	 * 根据内容分类的id，查询其下的内容列表
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> getContentListByCategoryId(Long categoryId);
}
