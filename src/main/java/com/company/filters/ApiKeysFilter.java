package com.company.filters;

import com.company.db.KeysDAODB;
import com.company.helpers.CookieHelper;
import com.company.services.KeysService;
import com.company.session.AdminSessionStorage;
import com.company.session.ApiKeysStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiKeysFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        KeysService.initKeys();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String key = servletRequest.getParameter("key");
        if(key!=null && ApiKeysStorage.containsKey(key)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.getWriter().println("Error of Authorization");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }
    }

    @Override
    public void destroy() {

    }
}
