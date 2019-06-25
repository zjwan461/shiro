package com.itsu.app.controller;

import com.itsu.app.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Su Ben on 2019/6/17
 */
@Controller
public class MyController {

    @GetMapping("/")
    public String idx() {
        return "index";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login.do")
    @ResponseBody
    public Map login(User user) {
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            if (user.isRememberMe())
                token.setRememberMe(true);
            subject.login(token);
            map.put("code", "200");
            map.put("msg", "登录成功");
        } catch (AuthenticationException e) {
            map.put("code", "500");
            map.put("msg", e.getMessage());
        }

//        if (subject.hasRole("admin")) {
//            System.out.println("有admin权限");
//        }

        return map;
    }

    @RequiresRoles(value = {"admin"})
    @GetMapping("/testRole")
    public String testRole() {
        return "admin";
    }

    @ResponseBody
    @RequiresRoles(value = {"admin1"})
    @GetMapping(value = "/testRole1")
    public String testRole1() {
        return "admin1角色";
    }

}
