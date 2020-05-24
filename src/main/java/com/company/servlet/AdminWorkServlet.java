package com.company.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AdminWorkServlet extends HttpServlet {
    public AdminWorkServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("idModel", id);
        context.getRequestDispatcher("/admin_work.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tasks = request.getParameter("tasks");
        URL url = new URL("http://ml_real_time:8082");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        try(OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = tasks.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        String answer;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                builder.append(responseLine.trim());
            }
            answer = builder.toString();
        }
        response.getWriter().print(answer);
    }

}
