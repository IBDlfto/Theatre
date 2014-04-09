/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.theatre.servlets;

import bd.theatre.core.BDTickets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toure
 */
public class Reserver extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<String[]> panier = new ArrayList<String[]>();
        int length = Integer.parseInt(request.getParameter("length"));
        int montant = Integer.parseInt(request.getParameter("montant"));
        for (int i = 0; i < length; i++) {
            panier.add(request.getParameterValues("cookie[" + i + "][]"));
        }
        BDTickets form = new BDTickets(); 
        out.println(form.allInOne(panier, montant));
    }
}
