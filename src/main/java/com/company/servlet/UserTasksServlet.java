package com.company.servlet;

import com.company.db.ModelsDAODB;
import com.company.db.QueueDAODB;
import com.company.enitities.ModelEntity;
import com.company.enitities.QueueTaskAdminEntity;
import com.company.enitities.QueueTaskUserEntity;
import com.company.enitities.UserEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.helpers.CookieHelper;
import com.company.services.ModelsService;
import com.company.services.QueueService;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 400,
        maxRequestSize = 1024 * 1024 * 512)
@WebServlet(
        name = "userTasksServlet",
        urlPatterns = { "/user/tasks/*"},
        loadOnStartup = 1
)
public class UserTasksServlet extends HttpServlet {
    public UserTasksServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            QueueService service = new QueueService(new QueueDAODB());
            ModelsService modelsService = new ModelsService(new ModelsDAODB());

            request.setAttribute("models", modelsService.getAllCompletedModels());
            String session = CookieHelper.getCookieByName(request,"userSession");
            UserEntity user = UserSessionStorage.getUser(session);
            request.setAttribute("user", user);
            request.setAttribute("tasks", service.getAllQueueTaskUserByUserId(user.getId()));
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/user_add_tasks.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QueueTaskUserEntity task = new QueueTaskUserEntity();

        task.setId(request.getParameterMap().containsKey("id")
                ? Integer.parseInt(request.getParameter("id"))
                : -1);
        task.setTitle(request.getParameter("title"));
        ServletContext context = this.getServletContext();
        try {
            QueueService service = new QueueService(new QueueDAODB());

            if(request.getParameterMap().containsKey("save")){
                int modelId = Integer.parseInt(request.getParameter("modelId"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                Part filePart = request.getPart("taskFile"); // Retrieves <input type="file" name="file">
                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);
                ModelEntity modelEntity =  new ModelEntity();
                modelEntity.setId(modelId);
                task.setModel(modelEntity);
                UserEntity user = new UserEntity();
                user.setId(userId);
                task.setUser(user);
                service.AddUserTextTask(task, buffer);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.UpdateUserTextTask(task);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.DeleteUserTextTask(task);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("tasks", service.getAllQueueTaskAdmin());

        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/user_add_tasks.jsp").forward(request, response);
    }
}
