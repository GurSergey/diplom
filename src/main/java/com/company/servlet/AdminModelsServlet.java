package com.company.servlet;

import com.company.dao.ModelsDAO;
import com.company.db.DatasetDAODB;
import com.company.db.ModelsDAODB;
import com.company.enitities.DatasetEntity;
import com.company.enitities.ModelEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.DataSetService;
import com.company.services.ModelsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminModelsServlet extends HttpServlet {
    public AdminModelsServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            ModelsService service = new ModelsService(new ModelsDAODB());
            request.setAttribute("models", service.getModels());
            request.setAttribute("datasets", new DataSetService(new DatasetDAODB()).getAllDataSet());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/models.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelEntity modelEntity = new ModelEntity();

        modelEntity.setId(request.getParameterMap().containsKey("id") ? Integer.parseInt(request.getParameter("id"))
                : -1);
        modelEntity.setTitle( request.getParameter("title"));

        ServletContext context = this.getServletContext();
        try {
            ModelsService service = new ModelsService(new ModelsDAODB());

            if(request.getParameterMap().containsKey("save")){
                DatasetEntity datasetEntity = new DatasetEntity();
                datasetEntity.setId(Integer.parseInt(request.getParameter("datasetId")));
                service.saveModel(modelEntity, datasetEntity);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updateModel(modelEntity);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteModel(modelEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("models", service.getModels());
//            DatasetEntity[] test = new DataSetService(new DatasetDAODB()).getAllDataSet();
            request.setAttribute("datasets", new DataSetService(new DatasetDAODB()).getAllDataSet());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/models.jsp").forward(request, response);
    }

}
