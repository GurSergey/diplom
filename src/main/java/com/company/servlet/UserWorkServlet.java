package com.company.servlet;

import com.company.db.ModelsDAODB;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.services.ModelsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserWorkServlet extends HttpServlet {
    public UserWorkServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.getRequestDispatcher("/work.jsp").forward(request, response);
    }
}
