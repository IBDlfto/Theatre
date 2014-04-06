/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Place;
import bd.theatre.exceptions.PlaceException;
import bd.theatre.exceptions.ExceptionConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fagno
 */
public class BDPlaces {

    public String error;

    public List<Place> getPlaces(HttpServletRequest request)
            throws PlaceException, ExceptionConnexion {

        int numS = Integer.parseInt(request.getParameter("noRep"));
        String dateRep = request.getParameter("dateRep");
        List<Place> res = new ArrayList<Place>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM LesPlaces "
                    + "MINUS "
                    + "SELECT t.noPlace, t.noRang, p.numZ FROM LesTickets t "
                    + "INNER JOIN lesrepresentations r on t.daterep = r.daterep "
                    + "INNER JOIN lesplaces p on (t.noPlace = p.noPlace AND t.noRang = p.noRang) "
                    + "WHERE t.numS = ? AND t.daterep = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setTimestamp(2, Timestamp.valueOf(dateRep));
            rs = stmt.executeQuery();
            while (rs.next()) {
                res.add(new Place(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            throw new PlaceException(" Problème dans l'interrogation des Places.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
        return res;
    }
}
