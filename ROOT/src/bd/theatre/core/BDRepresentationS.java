/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Representation;
import bd.theatre.beans.Spectacle;
import bd.theatre.exceptions.ExceptionConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author toure
 */
public class BDRepresentationS {

    public String error;

     public List<Representation> getSpectacle(int numS/*HttpServletRequest request*/) {

        numS = 101;//Integer.parseInt(request.getParameter("numS"));
        List<Representation> res = new ArrayList<Representation>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM LesRepresentations WHERE numS = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.add(new Representation(rs.getInt(1), rs.getString(2)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
        return res;
    }
    public static void main(String[] args) {
        BDRepresentationS form = new BDRepresentationS();
        List<Representation> list = form.getSpectacle(101);
        System.out.println(form.error);
        for(Representation r : list) {
            System.out.println(r.getDate());
        }
    }
}
