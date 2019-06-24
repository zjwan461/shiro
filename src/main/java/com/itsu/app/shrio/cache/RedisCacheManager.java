package com.itsu.app.shrio.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * @author 苏犇
 * @date 2019/6/24 19:33
 */
public class RedisCacheManager implements CacheManager {


    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return redisCache;
    }
}