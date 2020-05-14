package com.company.servlet;

import com.company.enums.Roles;
import com.company.helpers.CookieHelper;
import com.company.session.AdminSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class AdminAuthServlet extends HttpServlet {

    private static final int SIZE_SESSION_ID = 100;
    private static final String LOGIN_ADMIN = "admin";
    private static final String PASS_ADMIN = "123";

    private static final String PASS_PARAMETER = "password";
    private static final String LOGIN_PARAMETER = "login";

    public AdminAuthServlet(){
        super();
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

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

        String session = CookieHelper.getCookieByName(request,CookieHelper.ADMIN_SESSION);
        if(session!= null && AdminSessionStorage.sessionIsActive(session)) {

        } else {
            ServletContext context = getServletContext();
            context.getRequestDispatcher("/auth.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String password = request.getParameter(PASS_PARAMETER);
        String login = request.getParameter(LOGIN_PARAMETER);
        if(password.equals(PASS_ADMIN) && login.equals(LOGIN_ADMIN)){
            String sessionId = generateSessionId();
            CookieHelper.setCookieByName( request, response,  CookieHelper.ADMIN_SESSION,
                    sessionId, request.getContextPath() + "/admin");
            AdminSessionStorage.setSession(sessionId);
            response.sendRedirect(request.getContextPath() + "/admin/menu");
            return;
        }  else {
            request.setAttribute("authPassed", false);
        }
        ServletContext context = getServletContext();
        context.getRequestDispatcher("/auth.jsp").forward(request, response);
    }
}
