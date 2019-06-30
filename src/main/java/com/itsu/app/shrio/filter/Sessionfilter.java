package com.itsu.app.shrio.filter;

import com.itsu.app.utils.ProjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 苏犇
 * @date 2019/6/27 11:44
 */

public class Sessionfilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (super.isLoginRequest(request, response)) {
            return Boolean.TRUE;
        }

        Subject subject = super.getSubject(request, response);
        if (subject.isAuthenticated()) {
            return Boolean.TRUE;
        } else {
            if (ProjectUtil.isAjax((HttpServletRequest) request)) {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                PrintWriter writer = servletResponse.getWriter();
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.setContentType("applicaton/json;chartset=utf-8");
                writer.write("session time out");
                writer.flush();
                writer.close();
            }
            return Boolean.FALSE;
        }

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        super.saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
