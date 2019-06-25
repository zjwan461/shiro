package com.itsu.app.shrio.cache;

import com.itsu.app.utils.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @author 苏犇
 * @date 2019/6/24 19:38
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private JedisUtil jedisUtil;

    private RedisSerializer serializer = new JdkSerializationRedisSerializer();

    private final String cache_prefix = "cache";

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String name, JedisUtil jedisUtil) {
        this.cacheName = name;
        this.jedisUtil = jedisUtil;
    }

    private String getKey(K key) {
        return cache_prefix + ":" + key + ":" + this.cacheName;
    }


    @Override
    public V get(K key) throws CacheException {
        byte[] value = jedisUtil.get(getKey(key));
        if (value != null) {
            return (V) serializer.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        String k = getKey(key);
        byte[] v = serializer.serialize(value);
        jedisUtil.set(k, v);
//        jedisUtil.expire(k, 600);

        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        String k = getKey(key);
        byte[] v = jedisUtil.get(k);
        jedisUtil.del(k);
        if (v != null) {
            return (V) SerializationUtils.deserialize(v);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
