package com.company.filters;


import com.company.helpers.CookieHelper;
import com.company.session.UserSessionStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String session = CookieHelper.getCookieByName((HttpServletRequest) servletRequest, "userSession");
        if(session!= null && UserSessionStorage.getUser(session)!=null) {
            servletRequest.setAttribute("userName", UserSessionStorage.getUser(session).getLogin());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            CookieHelper.deleteCookie(httpResponse, "userSession");
            httpResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/user_auth/");
        }
    }

    @Override
    public void destroy() {

    }
}
