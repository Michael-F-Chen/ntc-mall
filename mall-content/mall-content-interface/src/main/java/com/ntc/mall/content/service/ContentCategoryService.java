package com.ntc.mall.content.service;

import java.util.List;

import com.ntc.mall.common.pojo.EasyUITreeNode;
import com.ntc.mall.common.pojo.MallResult;

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

	/**
	 * 添加内容分类
	 * @param parentId
	 * @param name
	 * @return
	 */
	public MallResult createContentCategory(Long parentId, String name);

	/**
	 * 删除节点
	 * @param id
	 * @return
	 */
	public MallResult deleteContentCategory(Long id);
}
