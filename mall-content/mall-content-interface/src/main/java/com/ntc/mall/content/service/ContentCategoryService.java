package com.ntc.mall.content.service;

import java.util.List;

import com.ntc.mall.common.pojo.EasyUITreeNode;

/**
 * @author Michael-Chen
 */
public interface ContentCategoryService {
	
	/**
	 * 通过节点id查询子节点
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoruList(Long parentId);
}
