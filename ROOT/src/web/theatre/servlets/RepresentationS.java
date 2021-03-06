/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.theatre.servlets;

import bd.theatre.beans.Representation;
import bd.theatre.core.BDRepresentationS;
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
public class RepresentationS extends HttpServlet {
public final static String VUE = "/admin/representations.jsp";
public final static String VUE_PUBLIC = "/public/representations.jsp";
    public final static String REPRESENTATION = "representations";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BDRepresentationS form = new BDRepresentationS();
        List<Representation> representations = form.getRepresentation(request);
        request.setAttribute("error", form.error);
        request.setAttribute(REPRESENTATION, representations);
        if(request.getParameter("nom") != null) {
            request.setAttribute("nom", request.getParameter("nom"));
            request.setAttribute("numS", request.getParameter("numS"));
            this.getServletContext().getRequestDispatcher(VUE_PUBLIC).forward(request, response);
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
