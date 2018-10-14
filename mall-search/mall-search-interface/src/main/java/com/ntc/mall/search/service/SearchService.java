package com.ntc.mall.search.service;

import com.ntc.mall.common.pojo.MallResult;

/**
 * @author Michael-Chen
 */
public interface SearchService {

	/**
	 * 导入所有的商品数据到索引库中
	 * @return
	 */
	public MallResult importAllSearchItems() throws Exception;
}
