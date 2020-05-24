package com.company.servlet;

import com.company.db.DatasetDAODB;
import com.company.db.QueueDAODB;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.services.DataSetService;
import com.company.services.QueueService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAllTasksServlet extends HttpServlet {
    public AdminAllTasksServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            QueueService service = new QueueService(new QueueDAODB());
            request.setAttribute("taskMl", service.getAllMLTask());
            request.setAttribute("taskCheck", service.getAllQueueCheckTasks());
            request.setAttribute("taskMerge", service.getAllQueueMergeTasks());
            request.setAttribute("taskAdmin", service.getAllQueueTaskAdmin());
            request.setAttribute("taskUser", service.getAllQueueTaskUser());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/queue.jsp").forward(request, response);
    }


}
