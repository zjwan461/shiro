package com.itsu.app.session;

import com.itsu.app.utils.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 苏犇
 * @date 2019/6/24 14:26
 */
public class RedisSessionDao extends AbstractSessionDAO {

    @Resource
    private JedisUtil jedisUtil;

    private final String session_prefix = "shiro:session";

    private String getKey(String key) {

        return session_prefix + ":" + key;
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            String key = getKey(session.getId().toString());
            byte[] sessionValue = SerializationUtils.serialize(session);
            jedisUtil.set(key, sessionValue);
//            jedisUtil.expire(key,);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionid = super.generateSessionId(session);
        super.assignSessionId(session, sessionid);
        saveSession(session);
        return sessionid;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        String key = getKey(sessionId.toString());
        byte[] sessionByts = jedisUtil.get(key);

        Session session = (Session) SerializationUtils.deserialize(sessionByts);
        return session;

    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            jedisUtil.del(getKey(session.getId().toString()));
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keys = jedisUtil.keys(session_prefix);
        Set<Session> values = new HashSet<>();
        for (String key : keys) {
            Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
            values.add(session);
        }
        return values;
    }
}