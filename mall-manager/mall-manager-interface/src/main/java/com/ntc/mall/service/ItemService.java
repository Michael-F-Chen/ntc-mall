package com.ntc.mall.service;

import com.ntc.mall.common.pojo.EasyUIDataGridResult;

/**
 * 商品相关的Service
 * @author Michael-Chen
 */
public interface ItemService {

	/**
	 * 根据传入的页码和每页行数进行分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer page, Integer rows);
}
