package com.ntc.mall.search.service;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.common.pojo.SearchResult;

/**
 * @author Michael-Chen
 */
public interface SearchService {

	/**
	 * 导入所有的商品数据到索引库中
	 * @return
	 */
	public MallResult importAllSearchItems() throws Exception;
	
	/**
	 * 根据搜索条件搜索结果
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(String queryString, Integer page, Integer rows) throws Exception;
}
