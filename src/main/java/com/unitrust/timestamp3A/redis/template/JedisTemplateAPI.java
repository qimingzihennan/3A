package com.unitrust.timestamp3A.redis.template;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisTemplateAPI {
	public void hmset(String redisKey, Map<String, String> redisKeyValueMap);

	public void hincrByFloat(String redisKey, String keyColum, Double value);

	public Map<String, String> hgetAll(String redisKey);

	public Set<String> zrange(String redisKey, Long start, Long end);

	public Long zcard(String redisKey);

	public Long zremrangeByRank(String key, long start, long end);

	public List<String> brpop(int timeout, String key);

	public String hget(String key, String field);

	public void hset(String redisKey, String field, String value);
}
