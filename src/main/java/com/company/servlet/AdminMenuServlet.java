package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.services.PollsService;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminMenuServlet extends HttpServlet {
    public AdminMenuServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.getRequestDispatcher("/menu.jsp").forward(request, response);
    }
}
