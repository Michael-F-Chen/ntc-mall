package com.ntc.mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntc.mall.common.pojo.SearchItem;
import com.ntc.mall.common.pojo.SearchResult;

/**
 * 从索引库里搜索商品的dao
 * @author Michael-Chen
 */
@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 根据查询的条件查询结果集
	 * @param solrQuery
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		
		SearchResult searchResult = new SearchResult();
		
		// 1.创建solrserver对象,由spring管理注入
		
		// 2.执行查询
		QueryResponse response = solrServer.query(solrQuery);
		
		// 3.获取结果集
		SolrDocumentList results = response.getResults();
		
		// 设置searchResult的总记录数
		searchResult.setRecordCount(results.getNumFound());
		
		// 4.遍历结果集
		List<SearchItem> searchItemList = new ArrayList<>();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : results) {
			SearchItem searchItem = new SearchItem();
			searchItem.setCategory_name(solrDocument.get("item_category_name").toString());
			searchItem.setId(Long.parseLong(solrDocument.get("id").toString()));
			searchItem.setImage(solrDocument.get("item_image").toString());
			
			searchItem.setPrice((Long)solrDocument.get("item_price"));
			searchItem.setSell_point(solrDocument.get("item_sell_point").toString());
			
			//取高亮
			String highlightingStr = "";
			List<String> list = highlighting.get(solrDocument.get("id")).get(solrDocument.get("item_title"));
			if(list != null && list.size() > 0){
				highlightingStr = list.get(0);
			}else{
				highlightingStr = solrDocument.get("item_title").toString();	
			}
			
			searchItem.setTitle(highlightingStr);
			searchItemList.add(searchItem);
		}
		
		searchResult.setItemList(searchItemList);
		return searchResult;
	}
}
