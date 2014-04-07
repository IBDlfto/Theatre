/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Representation;
import bd.theatre.exceptions.ExceptionConnexion;
import bd.theatre.utils.Fonctions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author toure
 */
public class BDRepresentationS {

    public String error;

    public List<Representation> getRepresentation(HttpServletRequest request) {

        int numS = Integer.parseInt(request.getParameter("numS"));

        List<Representation> res = new ArrayList<Representation>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM LesRepresentations WHERE numS = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String date = Fonctions.formatter(rs.getDate(2));
                Representation rep = new Representation(numS, date);
                rep.setDateF(Fonctions.format(rs.getDate(2)));
                res.add(rep);
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
        return res;
    }

    public String addRepresentation(HttpServletRequest request) {
        String res = null;
        int numS = Integer.parseInt(request.getParameter("numS"));
        String date = request.getParameter("date");

        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "INSERT INTO LesRepresentations values(?, ?)";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setTimestamp(2, Timestamp.valueOf(date));
            if (stmt.executeUpdate() > 0) {
                String dateret = Fonctions.formatter(new Date(Timestamp.valueOf(date).getTime()));
                res = "{\"error\": false, \"message\": \"Réprensentation ajoutée avec succès\","
                        + " \"date\": \"" + dateret + "\"}";
            } else {
                res = "{\"error\": true, \"message\": \"Une erreur s'est produite lors de l'ajout\"}";
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            res = "{\"error\": true, \"message\": \"" + e.getMessage() + "\"}";
        } catch (ExceptionConnexion ex) {
            res = "{\"error\": true, \"message\": \"" + ex.getMessage() + "\"}";
        }
        return res;
    }
}
