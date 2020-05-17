package com.company.servlet;

import com.company.db.UserDAODB;
import com.company.enitities.UserEntity;
import com.company.exceptions.SelectException;
import com.company.helpers.CookieHelper;
import com.company.services.UserService;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

public class UserAuthServlet extends HttpServlet {
    public UserAuthServlet(){
        super();
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

    private static final int SIZE_SESSION_ID = 100;

    private static String generateSessionId() {
//        if (SIZE_SESSION_ID < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(SIZE_SESSION_ID);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < SIZE_SESSION_ID; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);
            sb.append(rndChar);

        }

        return sb.toString();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean authPassed = false;
        String session = CookieHelper.getCookieByName(request,"userSession");
        if(session!= null && UserSessionStorage.getUser(session)!=null)
            authPassed = true;
        ServletContext context = getServletContext();
        request.setAttribute("authPassed", authPassed);
        context.getRequestDispatcher("/auth_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        UserService service = new UserService(new UserDAODB());
        ServletContext context = getServletContext();
        try {
            UserEntity voter = service.getUserByLoginPassword(login, password);
            if(voter != null){
                String sessionId = generateSessionId();
                CookieHelper.setCookieByName(request, response, "userSession", sessionId,
                        request.getContextPath() + "/user");
                UserSessionStorage.setSession(sessionId, voter);
                request.setAttribute("authPassed", true);
                response.sendRedirect(request.getContextPath() + "/user/models");
                return;
            } else {
                request.setAttribute("authPassed", false);
            }
        } catch (SelectException e) {
            e.printStackTrace();
        }

        context.getRequestDispatcher("/auth_user.jsp").forward(request, response);
    }
}
