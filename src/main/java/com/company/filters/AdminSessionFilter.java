package com.company.filters;

import com.company.helpers.CookieHelper;
import com.company.session.AdminSessionStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSessionFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String session = CookieHelper.getCookieByName((HttpServletRequest) servletRequest, "adminSession");
        if(session!=null && AdminSessionStorage.sessionIsActive(session)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            CookieHelper.deleteCookie(httpResponse, "adminSession");
            httpResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/admin_auth/");
        }
    }

    @Override
    public void destroy() {

    }
}
