package com.unitrust.timestamp3A.redis.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unitrust.timestamp3A.redis.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisClientTemplate implements JedisTemplateAPI {
	private static final Logger LOG = LoggerFactory.getLogger(RedisClientTemplate.class);

	private JedisUtil jedisUtil;

	public JedisUtil getJedisUtil() {
		return jedisUtil;
	}

	public void setJedisUtil(JedisUtil jedisUtil) {
		this.jedisUtil = jedisUtil;
	}

	@Override
	public void hmset(String redisKey, Map<String, String> redisKeyValueMap) {
		Jedis jedis = jedisUtil.getJedis();
		if (jedis == null) {
			LOG.error("jedtis为空");
			return;
		}
		boolean broken = false;
		try {
			jedis.hmset(redisKey, redisKeyValueMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
	}

	@Override
	public void hincrByFloat(String redisKey, String keyColum, Double value) {
		Jedis jedis = jedisUtil.getJedis();
		if (jedis == null) {
			LOG.error("jedtis为空");
			return;
		}
		boolean broken = false;
		try {
			jedis.hincrByFloat(redisKey, keyColum, value);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
	}

	@Override
	public Map<String, String> hgetAll(String redisKey) {
		Jedis jedis = jedisUtil.getJedis();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (jedis == null) {
			LOG.error("jedtis为空");
			return resultMap;
		}
		boolean broken = false;
		try {
			resultMap = jedis.hgetAll(redisKey);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return resultMap;
	}

	@Override
	public Set<String> zrange(String redisKey, Long start, Long end) {
		Jedis jedis = jedisUtil.getJedis();
		Set<String> resultSet = new HashSet<String>();
		if (jedis == null) {
			LOG.error("jedtis为空");
			return resultSet;
		}
		boolean broken = false;
		try {
			resultSet = jedis.zrange(redisKey, start, end);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return resultSet;
	}

	@Override
	public Long zcard(String redisKey) {
		Jedis jedis = jedisUtil.getJedis();
		Long result = 0L;
		if (jedis == null) {
			LOG.error("jedtis为空");
			return result;
		}
		boolean broken = false;
		try {
			result = jedis.zcard(redisKey);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		Jedis jedis = jedisUtil.getJedis();
		Long result = 0L;
		if (jedis == null) {
			LOG.error("jedtis为空");
			return result;
		}
		boolean broken = false;
		try {
			result = jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		Jedis jedis = jedisUtil.getJedis();
		List<String> result = new ArrayList<String>();
		if (jedis == null) {
			LOG.error("jedtis为空");
			return result;
		}
		boolean broken = false;
		try {
			result = jedis.brpop(timeout, key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisUtil.getJedis();
		String result = "";
		if (jedis == null) {
			LOG.error("jedtis为空");
			return result;
		}
		boolean broken = false;
		try {
			result = jedis.hget(key, field);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public void hset(String redisKey, String field, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisUtil.getJedis();
		String result = "";
		if (jedis == null) {
			LOG.error("jedtis为空");

		}
		boolean broken = false;
		try {
			jedis.hset(redisKey, field, value);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
	}

	@Override
	public boolean exists(String key) {
		Boolean result = false;
		Jedis jedis = jedisUtil.getJedis();
		if (jedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			broken = true;
		} finally {
			jedisUtil.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public Boolean deleteKey(String oldRedisKey) {
		Jedis jedis = jedisUtil.getJedis();
		boolean result = true;
		if (jedis == null) {
			LOG.error("jedtis为空");
			result= false;
			return result;
		}
		try {
			Long abc = jedis.del(oldRedisKey);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
}
