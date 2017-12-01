package com.unitrust.timestamp3A.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class JeditsClusterUtil {
	private static final Logger LOG = LoggerFactory.getLogger(JedisUtil.class);

	private JedisCluster jedisClusterClient;

	public JedisCluster getJedisClusterClient() {
		return jedisClusterClient;
	}

	public void setJedisClusterClient(JedisCluster jedisClusterClient) {
		this.jedisClusterClient = jedisClusterClient;
	}

	public JedisCluster getJedis() {
		return jedisClusterClient;
	}

}
