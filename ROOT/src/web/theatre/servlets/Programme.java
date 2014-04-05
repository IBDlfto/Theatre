package web.theatre.servlets;

/*
 * @(#)Programme.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Proramme Servlet.
 *
 * This servlet dynamically returns the theater program.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */
public class Programme extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("text/html");
        out.println("<HEAD><TITLE> Programme de la saison </TITLE></HEAD>");
        out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
        out.println("<font color=\"#FFFFFF\"><h1> Programme de la saison </h1>");

	  // TO DO
        // Récupération de la liste de tous les spectacles de la saison.
        // Puis construction dynamique d'une page web décrivant ces spectacles.
        out.println("<p><i><font color=\"#FFFFFF\">A compl&eacute;ter</i></p>");
        out.println("<p><i><font color=\"#FFFFFF\">...</i></p>");

        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Accueil</a></p>");
        out.println("</BODY>");
        out.close();
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
    public String getServletInfo() {
        return "Retourne le programme du théatre";
    }
}
