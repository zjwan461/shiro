package com.itsu.app.test;

import com.itsu.app.utils.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author 苏犇
 * @date 2019/6/24 17:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class SpringRedisTestor {

    @Resource
    private JedisUtil jedisUtil;

    @Test
    public void testJedis() {
        jedisUtil.set("suben", "admin".getBytes());
    }

    @Test
    public void testGet() {

    }
}
