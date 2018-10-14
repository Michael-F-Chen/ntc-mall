package com.ntc.mall.search.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

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
}
