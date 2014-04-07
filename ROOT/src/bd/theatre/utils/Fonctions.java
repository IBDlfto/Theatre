/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd.theatre.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toure
 */
public class Fonctions {
    
    public static String formatter(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMMM yyyy 'à' hh:mm");
        return df.format(dt);
    }
    
    public static String format(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(dt);
    }
    
    public static String getCookieValue(HttpServletRequest request, String nom) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && nom.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
        Cookie cookie = new Cookie(nom, valeur);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/public/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
