package com.ntc.mall.search.mapper;

import java.util.List;

import com.ntc.mall.common.pojo.SearchItem;

/**
 * 定义Mapper 关联查询3张表，查询出搜索是的商品书记
 * @author Michael-Chen
 */
public interface SearchItemMapper {

	/**
	 * 查询所有的item
	 * @return
	 */
	public List<SearchItem> getSearchItemList();
}
