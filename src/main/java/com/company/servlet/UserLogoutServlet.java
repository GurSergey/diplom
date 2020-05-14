package com.company.servlet;

import com.company.helpers.CookieHelper;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLogoutServlet extends HttpServlet {
    public UserLogoutServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        String session = CookieHelper.getCookieByName(request,"userSession");
        if(session!= null && UserSessionStorage.getUser(session) != null)
            UserSessionStorage.deleteSession(session);
        context.getRequestDispatcher("/logout.jsp").forward(request, response);
    }
}
