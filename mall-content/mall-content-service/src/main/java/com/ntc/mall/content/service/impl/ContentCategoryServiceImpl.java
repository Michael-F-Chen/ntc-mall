package com.ntc.mall.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntc.mall.common.pojo.EasyUITreeNode;
import com.ntc.mall.content.service.ContentCategoryService;
import com.ntc.mall.mapper.TbContentCategoryMapper;
import com.ntc.mall.pojo.TbContentCategory;
import com.ntc.mall.pojo.TbContentCategoryExample;
import com.ntc.mall.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类
 * @author Michael-Chen
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper mapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoruList(Long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		
		// 创建条件
		Criteria crteria = example.createCriteria();
		crteria.andParentIdEqualTo(parentId);
		// select * from tbcontentcategory where parent_id = ?
		
		// 执行查询
		List<TbContentCategory> list = mapper.selectByExample(example);
		
		// 转成EasyUITreeNode 列表
		List<EasyUITreeNode> nodes = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			// 是否是父节点
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 分类名称
			node.setText(tbContentCategory.getName());
			nodes.add(node);
		}
		
		return nodes;
	}

}
