package com.company.servlet;

import com.company.db.KeysDAODB;
import com.company.db.ModelsDAODB;
import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.enitities.ModelEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.KeysService;
import com.company.services.ModelsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminKeysServlet extends HttpServlet {
    public AdminKeysServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            KeysService service = new KeysService(new KeysDAODB());
            request.setAttribute("keys", service.getAllKeys());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("keys.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        KeyEntity keyEntity = new KeyEntity();

        keyEntity.setId(request.getParameterMap().containsKey("id")
                ? Integer.parseInt(request.getParameter("id"))
                : -1);
        keyEntity.setName( request.getParameter("title"));
        keyEntity.setKeyStr(request.getParameter("keyStr"));
        ServletContext context = this.getServletContext();
        try {
            KeysService service = new KeysService(new KeysDAODB());

            if(request.getParameterMap().containsKey("save")){
                service.saveKey(keyEntity);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updateKey(keyEntity);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteKey(keyEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("polls", service.getAllKeys());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/keys.jsp").forward(request, response);
    }
}
