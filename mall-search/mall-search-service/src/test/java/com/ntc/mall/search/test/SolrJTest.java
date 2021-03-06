package com.ntc.mall.search.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

/**
 * @author Michael-Chen
 */
public class SolrJTest {

	/**
	 * 测试向solr中添加测试数据
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception {
		// 1.创建 solrserver 建立连接，需要制定地址
		SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8079/solr");

		// 2.创建solrinputdocument
		SolrInputDocument document = new SolrInputDocument();
		
		// 3.想文档中添加域
		document.addField("id", "test001");
		document.addField("item_title", "这是一个测试");
		
		// 4.将文档提交到索引库中
		solrServer.add(document);
		
		// 5.提交
		solrServer.commit();
	}
	
	/**
	 * 测试从索引库中搜索数据
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception{
		// 1.创建solrserver对象
		SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8079/solr");
		
		// 2.创建solrquery对象， 设置各种过滤条件，主查询条件 排序
		SolrQuery query = new SolrQuery();
		
		// 3.设置查询条件
		query.setQuery("阿尔卡特");
		query.addFilterQuery("item_price [0 TO 300000000]");
		query.set("df", "item_title");	// 设置定义域
			
		// 4.执行查询
		QueryResponse response = solrServer.query(query);
		
		// 5.获取结果集
		SolrDocumentList results = response.getResults();
		System.out.println("查询的总记录数： " + results.getNumFound());
		
		// 6.遍历结果集打印
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
		}
	}
}
