package com.itsu.app.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 苏犇
 * @date 2019/6/27 11:45
 */

public class ProjectUtil {

    public static boolean isAjax(HttpServletRequest request) {
        boolean result = false;
        if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equals("XMLHttpRequest")) {
            result = true;
        }
        return result;
    }
}
