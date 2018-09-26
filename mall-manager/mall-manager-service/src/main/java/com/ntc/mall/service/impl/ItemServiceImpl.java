package com.ntc.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ntc.mall.common.pojo.EasyUIDataGridResult;
import com.ntc.mall.mapper.TbItemMapper;
import com.ntc.mall.pojo.TbItem;
import com.ntc.mall.pojo.TbItemExample;
import com.ntc.mall.service.ItemService;

/**
 * @author Michael-Chen
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	
	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		// 1.设置分页的信息，使用pagehelper;
		if(page == null) page = 1;
		if(rows == null) rows = 30;
		PageHelper.startPage(page, rows);
		
		// 2.创建example对象，不需要设置查询对象
		TbItemExample example = new TbItemExample();
		
		// 3.根据mapper调用查询所有数据的方法
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		// 4.获取分页的信息
		PageInfo<TbItem> info = new PageInfo<>(list);
		
		// 5.封装到EasyUIDataGridResult
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(info.getTotal());
		result.setRows(info.getList());
		
		// 6.返回
		return result;
	}

}
