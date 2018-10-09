package com.ntc.mall.test.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * @author Michael-Chen
 */
public class JedisTest {

	/**
	 * 测试单机版jedis
	 */
	@Test
	public void testJedis(){
		// 1.创建Jedis对象，需要制定连接的地址和端口
		Jedis jedis = new Jedis();
		// 2.直接创建redis set
		jedis.set("key1234", "value");
		System.out.println(jedis.get("key1234"));
		// 3.关闭Jedis
		jedis.close();
	}
}
