package com.company.servlet;

import com.company.db.UserDAODB;
import com.company.enitities.Entity;
import com.company.enitities.UserEntity;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.filters.UserSessionFilter;
import com.company.helpers.CookieHelper;
import com.company.services.UserService;
import com.company.session.UserSessionStorage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserDAODB());

        String session = CookieHelper.getCookieByName(request,"userSession");
        UserEntity user = UserSessionStorage.getUser(session);
        request.setAttribute("user", user);

        context.getRequestDispatcher("/edit_profile.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserDAODB());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Integer.parseInt(request.getParameter("id")));
        userEntity.setPassword(request.getParameter("password"));
        try {
            service.updateUser(userEntity);
            request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        }
        request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
        doGet(request,response);


    }
}
