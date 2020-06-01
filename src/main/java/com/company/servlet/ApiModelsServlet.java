package com.company.servlet;

import com.company.db.ModelsDAODB;
import com.company.enitities.ModelEntity;
import com.company.exceptions.SelectException;
import com.company.services.ModelsService;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApiModelsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelsService service = new ModelsService(new ModelsDAODB());
        ModelEntity[] modelEntities = new ModelEntity[0];
        try {
            modelEntities = service.getAllCompletedModels();
        } catch (SelectException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        //Converting the Object to JSONString
        String jsonString = gson.toJson(modelEntities);
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
//        response.getWriter().print(jsonString);
        out.println(jsonString);

    }

}
