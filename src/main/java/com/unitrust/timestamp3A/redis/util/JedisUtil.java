package com.unitrust.timestamp3A.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
	private static final Logger LOG = LoggerFactory.getLogger(JedisUtil.class);

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			LOG.error("[JedisDS] getRedisClent error:" + e.getMessage());
			if (null != jedis)
				jedis.close();
		}
		return null;
	}

	public void returnResource(Jedis jedis) {
		jedis.close();
	}

	public void returnResource(Jedis jedis, boolean broken) {
		jedis.close();
	}

}
