/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.theatre.servlets;

import bd.theatre.beans.Place;
import bd.theatre.core.BDPlaces;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author loic
 */
public class Places extends HttpServlet {

    public final static String VUE = "/public/places.jsp";
    public final static String PLACES = "places";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BDPlaces form = new BDPlaces();
        List<Place> places = form.getPlaces(request);
        request.setAttribute(PLACES, places);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
