package com.itsu.app.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 苏犇
 * @date 2019/6/26 0:46
 */
@ControllerAdvice
public class MyExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = RuntimeException.class)
    public Object handlerRuntimeException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        logger.error(e.getMessage());
        ModelAndView mv = new ModelAndView("/error/500");
        mv.addObject("msg", e.getMessage());
        return mv;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public Object handlerAuthenticationException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        logger.error(e.getMessage());
        Map map = new HashMap();
        map.put("code", "500");
        map.put("msg", "用户名或密码错误");
        return map;
    }

}
