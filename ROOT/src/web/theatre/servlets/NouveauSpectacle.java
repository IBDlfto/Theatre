/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.theatre.servlets;

import bd.theatre.core.BDSpectacles;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toure
 */
public class NouveauSpectacle extends HttpServlet {

    public final static String VUE = "/admin/nouveauSpectacle.jsp";
    public final static String SPECTACLE = "spectacles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String chemin = this.getServletContext().getRealPath("/") + "../../ROOT/web/uploads/";
        BDSpectacles form = new BDSpectacles();
        PrintWriter out = response.getWriter();
        out.println(form.addSpectacle(request, chemin));
    }
}
