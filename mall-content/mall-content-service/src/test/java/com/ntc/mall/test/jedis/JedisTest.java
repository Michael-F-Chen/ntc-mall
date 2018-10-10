package com.ntc.mall.test.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

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
		Jedis jedis = new Jedis("127.0.0.1",6379);
		// 2.直接创建redis set
		jedis.set("key1234", "value");
		System.out.println(jedis.get("key1234"));
		// 3.关闭Jedis
		jedis.close();
	}
	              
	@Test
	public void testJedisPool(){
		// 1.创建Jedis对象，需要制定连接的地址和端口
		JedisPool pool = new JedisPool("127.0.0.1",6379);
		// 2.创建jedis的对象
		Jedis jedis = pool.getResource();
		// 3.直接操作redis
		jedis.set("keypool", "keypool");
		System.out.println(jedis.get("keypool"));
		// 4.关闭Jedis (释放资源到连接时)
		jedis.close();
		// 5.关闭连接池（应用系统关闭时才关闭）
		pool.close();
	}
	
	/**
	 * 测试集群版jedis
	 */
	@Test
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("127.0.0.1",7001));
		nodes.add(new HostAndPort("127.0.0.1",7002));
		nodes.add(new HostAndPort("127.0.0.1",7003));
		nodes.add(new HostAndPort("127.0.0.1",7004));
		nodes.add(new HostAndPort("127.0.0.1",7005));
		nodes.add(new HostAndPort("127.0.0.1",7006));
		// 1.创建JedisCluster对象
		JedisCluster jedisCluster = new JedisCluster(nodes);
		// 2.直接操作jedisCluster
		jedisCluster.set("jedisCluster", "jedisCluster");
		System.out.println(jedisCluster.get("jedisCluster"));
		// 5.关闭jedisCluster（应用系统关闭时才关闭）封装了连接池
		jedisCluster.close();
	}
}
