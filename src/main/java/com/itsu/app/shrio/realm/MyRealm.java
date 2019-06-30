package com.itsu.app.shrio.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itsu.app.entity.User;
import com.itsu.app.mapper.UserMapper;
import com.itsu.app.utils.ByteSourceUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

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
        Set<String> roles = getRolesByUsername(userName);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /*模拟获取角色，实际情况应该从数据库获取*/
    private Set<String> getRolesByUsername(String userName) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");
        return roles;

    }

    //    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = getUserByUserName(userName);
        if (user == null) {
            return null;
        }
        int status = user.getStatus();
//        int status = 0;
        if (status != 0) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        /*设置密码加盐*/
        authenticationInfo.setCredentialsSalt(ByteSourceUtil.bytes(userName));
        return authenticationInfo;
    }

    public User getUserByUserName(String userName) {
        System.out.println("从数据库中获取数据");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }


}
