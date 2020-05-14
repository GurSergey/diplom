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

public class UserModelsServlet extends HttpServlet {
    public UserModelsServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            ModelsService service = new ModelsService(new ModelsDAODB());
            request.setAttribute("models", service.getModels());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/models_user.jsp").forward(request, response);
    }
}
