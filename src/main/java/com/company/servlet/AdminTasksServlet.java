package com.company.servlet;

import com.company.db.DatasetDAODB;
import com.company.enitities.DatasetEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.DataSetService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class AdminTasksServlet extends HttpServlet {
    public AdminTasksServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            DataSetService service = new DataSetService(new DatasetDAODB());
            request.setAttribute("datasets", service.getAllDataSet());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/datasets.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DatasetEntity datasetEntity = new DatasetEntity();

        datasetEntity.setId(request.getParameterMap().containsKey("id")
                ? Integer.parseInt(request.getParameter("id"))
                : -1);
        datasetEntity.setTitle(request.getParameter("title"));
        ServletContext context = this.getServletContext();
        try {
            DataSetService service = new DataSetService(new DatasetDAODB());

            if(request.getParameterMap().containsKey("save")){
                Part filePart = request.getPart("datasetFile"); // Retrieves <input type="file" name="file">
                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);
                service.saveDataset(datasetEntity, buffer);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updateDataset(datasetEntity);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteDataset(datasetEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("datasets", service.getAllDataSet());

        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/datasets.jsp").forward(request, response);
    }
}
