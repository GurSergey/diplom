package com.company.servlet;


import com.company.db.DatasetDAODB;
import com.company.db.KeysDAODB;
import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.DataSetService;
import com.company.services.KeysService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeError.getFileName;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 400,
        maxRequestSize = 1024 * 1024 * 512)
@WebServlet(
        name = "adminDatasetsServlet",
        urlPatterns = { "/admin/datasets/*"},
        loadOnStartup = 1
)
public class AdminDatasetServlet extends HttpServlet {
    public AdminDatasetServlet(){
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

        Part filePart = request.getPart("datasetFile"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        byte[] buffer = new byte[fileContent.available()];
        fileContent.read(buffer);
        String filename = "datasets"+File.separator+"123.txt";
        File targetFile = new File(filename);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);


        DatasetEntity datasetEntity = new DatasetEntity();

        datasetEntity.setId(request.getParameterMap().containsKey("id")
                ? Integer.parseInt(request.getParameter("id"))
                : -1);
        datasetEntity.setTitle(request.getParameter("title"));
        ServletContext context = this.getServletContext();
        try {
            DataSetService service = new DataSetService(new DatasetDAODB());

            if(request.getParameterMap().containsKey("save")){
                service.saveDataset(datasetEntity);
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
