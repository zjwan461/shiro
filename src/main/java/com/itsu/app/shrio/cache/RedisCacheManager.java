package com.itsu.app.shrio.cache;

import com.itsu.app.utils.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 苏犇
 * @date 2019/6/24 19:33
 */
public class RedisCacheManager implements CacheManager {

    private List<String> cacheNames;

    public void setCacheNames(List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache<K, V> cache = null;
        if (cacheNames.contains(name)) {
            cache = new RedisCache(name, jedisUtil);
        }
        return cache;
    }
}
