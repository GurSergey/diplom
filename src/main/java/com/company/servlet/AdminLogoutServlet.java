package com.company.servlet;

import com.company.helpers.CookieHelper;
import com.company.session.AdminSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        ServletContext context = getServletContext();
        String session = CookieHelper.getCookieByName(request,"adminSession");
        if(session!= null && AdminSessionStorage.sessionIsActive(session))
            AdminSessionStorage.deleteSession(session);
        context.getRequestDispatcher("/admin/logout.jsp").forward(request, response);
    }
}
