package com.ntc.mall.search.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.common.pojo.SearchItem;
import com.ntc.mall.common.pojo.SearchResult;
import com.ntc.mall.search.dao.SearchDao;
import com.ntc.mall.search.mapper.SearchItemMapper;
import com.ntc.mall.search.service.SearchService;

/**
 * @author Michael-Chen
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchItemMapper mapper;
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public MallResult importAllSearchItems() throws Exception {
		// 1.注入mapper
		
		// 2.调用mapper的方法，查询所有的商品数据
		List<SearchItem> searchItemList = mapper.getSearchItemList();
		
		// 3.通过solrJ 将数据写入索引库中
			// 创建httpsolrserver (已经在applicationContext-solr.xml定义过)
			// 创建solrinputdocument
		for(SearchItem searchItem : searchItemList){
			SolrInputDocument document = new SolrInputDocument();
			document.setField("id", searchItem.getId().toString());
			document.setField("item_title", searchItem.getTitle());
			document.setField("item_sell_point", searchItem.getSell_point());
			document.setField("item_price", searchItem.getPrice());
			document.setField("item_image", searchItem.getImage());
			document.setField("item_category_name", searchItem.getCategory_name());
			document.setField("item_desc", searchItem.getItem_desc());
			
			// 添加都索引库
			solrServer.add(document);
		}
		
		// 提交
		solrServer.commit();
		
		return MallResult.ok();
	}

	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
		// 1.创建solrquery对象
		SolrQuery solrQuery = new SolrQuery();

		// 2.设置主查询条件
		if (StringUtils.isNotBlank(queryString)) {
			solrQuery.setQuery(queryString);
		} else {
			solrQuery.setQuery("*:*");
		}
		// 设置过滤条件
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = 60;
		}
		// 设置默认的搜索域
		solrQuery.set("df", "item_keywords");
		// 设置高亮
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<em sytle=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		solrQuery.addHighlightField("item_title");	// 设置高亮显示的域
		
		// 3.调用dao的方法
		SearchResult search = searchDao.search(solrQuery);
		
		// 4.设置SearchResult的总页数
		long pageCount = 0;
		pageCount = (long) Math.ceil(search.getRecordCount()/rows);
		search.setPageCount(pageCount);
		
		return search;
	}

}
