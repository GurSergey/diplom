package com.company.servlet;

import com.company.db.DatasetDAODB;
import com.company.db.KeysDAODB;
import com.company.db.UserDAODB;
import com.company.enitities.KeyEntity;
import com.company.enitities.UserEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.DataSetService;
import com.company.services.KeysService;
import com.company.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUserServlet extends HttpServlet {

    public AdminUserServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        try {
            UserService service = new UserService(new UserDAODB());
            request.setAttribute("users", service.getAllUsers());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }

        getServletContext().getRequestDispatcher("/user_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(request.getParameterMap().containsKey("id")
                ? Integer.parseInt(request.getParameter("id"))
                : -1);
        userEntity.setLogin(request.getParameter("login"));
        userEntity.setPassword(request.getParameter("password"));
        ServletContext context = this.getServletContext();
        try {
            UserService service = new UserService(new UserDAODB());

            if(request.getParameterMap().containsKey("save")){
                service.createUser(userEntity);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
//            if(request.getParameterMap().containsKey( "update")){
//                service.(keyEntity);
//                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
//            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteUSer(userEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("users", service.getAllUsers());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
//        } catch (UpdateException e) {
//            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/user_list.jsp").forward(request, response);
    }
}
