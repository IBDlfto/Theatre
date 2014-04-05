/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.theatre.servlets;

import bd.theatre.beans.Spectacle;
import bd.theatre.core.BDSpectacles;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toure
 */
public class Public extends HttpServlet {

    public final static String VUE = "/public/index.jsp";
    public final static String SPECTACLE = "spectacles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BDSpectacles form = new BDSpectacles();
        List<Spectacle> spectacles = form.getSpectacle();
        request.setAttribute(SPECTACLE, spectacles);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
