package com.company.servlet;

import com.company.db.DatasetDAODB;
import com.company.db.QueueDAODB;
import com.company.enitities.DatasetEntity;
import com.company.enitities.QueueTaskAdminEntity;
import com.company.enitities.QueueTaskUserEntity;
import com.company.exceptions.SelectException;
import com.company.services.DataSetService;
import com.company.services.QueueService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AdminDownloadTaskServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 4096;
    public AdminDownloadTaskServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        QueueTaskAdminEntity taskUser = null;
        int id = Integer.parseInt(request.getParameter("id"));
        QueueService service = new QueueService(new QueueDAODB());
        try {
            taskUser = service.getByIdAdminTask(id);
        } catch (SelectException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment; filename="+taskUser.getFilename());
        File file = new File("../text_admin_file"+
                File.separator+taskUser.getFilename());
        try(InputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }

}
