package com.itsu.app.test;

import com.itsu.app.shrio.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by Su Ben on 2019/6/18
 */
public class ShrioTest {

    @Test
    public void testLogin() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        MyRealm realm = new MyRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("suben", "suben");
        subject.login(token);
        System.out.println("isAuthenticated" + subject.isAuthenticated());
    }
}
