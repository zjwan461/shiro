package com.itsu.app.shrio.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itsu.app.entity.User;
import com.itsu.app.mapper.UserMapper;
import com.itsu.app.utils.ByteSourceUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by Su Ben on 2019/6/17
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles();
        return authorizationInfo;
    }

    //    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper);
//        从数据库中获取授权数据
        System.out.println("从数据库中获取授权数据");
        if (user == null) {
            return null;
        }
        int status = user.getStatus();
//        int status = 0;
        if (status != 0) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(ByteSourceUtil.bytes(userName));
        return authenticationInfo;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("suben", "suben");
        System.out.println(md5Hash.toString());
    }
}
