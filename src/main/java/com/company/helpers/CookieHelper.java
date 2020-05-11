package com.company.helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
    public static final String  ADMIN_SESSION =  "adminSession";

    public static String getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        String cookieName = name;
        Cookie cookie;
        String value = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    value = cookie.getValue();
                    break;
                }
            }
        }
        return value;
    }

    private static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static void setCookieByName(HttpServletRequest request, HttpServletResponse response, String name, String value, String path) {
       Cookie cookie;
        if(getCookie(request,name) ==null){
             cookie = new Cookie(name, value);
            cookie.setPath(path);
        }
        else{
            cookie = getCookie(request,name);
            cookie.setValue(value);
        }

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

