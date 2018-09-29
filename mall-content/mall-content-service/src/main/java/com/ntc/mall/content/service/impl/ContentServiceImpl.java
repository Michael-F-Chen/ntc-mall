package com.ntc.mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.content.service.ContentService;
import com.ntc.mall.mapper.TbContentMapper;
import com.ntc.mall.pojo.TbContent;
import com.ntc.mall.pojo.TbContentExample;

/**
 * @author Michael-Chen
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper mapper;
	
	@Override
	public MallResult saveContent(TbContent content) {
		// 补全属性
		content.setCreated(new Date());
		content.setUpdated(content.getCreated());
		
		// 插入
		mapper.insertSelective(content);
		return MallResult.ok();
	}

	@Override
	public List<TbContent> getContentListByCategoryId(Long categoryId) {
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		// select * from tbcontent where category_id = 
		
		List<TbContent> list = mapper.selectByExample(example);
		return list;
	}

}
