package com.itsu.app.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 苏犇
 * @date 2019/6/24 14:26
 */
@Component
public class JedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public void set(String key, byte[] value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public byte[] get(String key) {
        return (byte[]) redisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public Set<String> keys(String keyPrefix) {
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        return keys;
    }

}
