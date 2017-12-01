package com.unitrust.timestamp3A.redis.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.unitrust.timestamp3A.redis.util.JeditsClusterUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisClientClusterTemplate implements JedisTemplateAPI {
	private static final Logger LOG = LoggerFactory.getLogger(RedisClientClusterTemplate.class);

	private JeditsClusterUtil jeditsClusterUtil;

	public JeditsClusterUtil getJeditsClusterUtil() {
		return jeditsClusterUtil;
	}

	public void setJeditsClusterUtil(JeditsClusterUtil jeditsClusterUtil) {
		this.jeditsClusterUtil = jeditsClusterUtil;
	}

	public void hmset(String redisKey, Map<String, String> redisKeyValueMap) {
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
	}

	@Override
	public void hincrByFloat(String redisKey, String keyColum, Double value) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
	}

	@Override
	public Map<String, String> hgetAll(String redisKey) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return resultMap;
	}

	@Override
	public Set<String> zrange(String redisKey, Long start, Long end) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return resultSet;
	}

	@Override
	public Long zcard(String redisKey) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return result;
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return result;
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return result;
	}

	@Override
	public String hget(String key, String field) {
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
		return result;
	}

	@Override
	public void hset(String redisKey, String field, String value) {
		// TODO Auto-generated method stub
		JedisCluster jedis = jeditsClusterUtil.getJedis();
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
		}
	}

}
