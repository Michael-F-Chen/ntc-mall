package com.ntc.mall.test.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntc.mall.jedis.JedisClient;

/**
 * 测试注入的单机版和集群版jedis
 * @author Michael-Chen
 */
public class TestJedisClient {

	@Test
	public void testJedis(){
		// 1.初始化spring容器
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");

		// 2.获取实现类的实例
		JedisClient bean = context.getBean(JedisClient.class);
		
		// 3.调用操作方法
		bean.set("jedisClientKey", "jedisClientKey");
		
		System.out.println(bean.get("jedisClientKey"));
	}
}
