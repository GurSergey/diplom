package com.company.servlet;

import com.company.db.DatasetDAODB;
import com.company.enitities.DatasetEntity;
import com.company.exceptions.SelectException;
import com.company.services.DataSetService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AdminDownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 4096;
    public AdminDownloadServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        DatasetEntity datasetEntity = null;
        int id = Integer.parseInt(request.getParameter("id"));
        DataSetService service = new DataSetService(new DatasetDAODB());
        try {
            datasetEntity = service.getById(id);
        } catch (SelectException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment; filename="+datasetEntity.getFilename());
        File file = new File("datasets"+
                File.separator+datasetEntity.getFilename());
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
