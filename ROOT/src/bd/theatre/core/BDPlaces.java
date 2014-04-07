/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Place;
import bd.theatre.exceptions.ExceptionConnexion;
import bd.theatre.utils.Fonctions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fagno
 */
public class BDPlaces {

    public String error;

    public List<Place> getPlaces(HttpServletRequest request) {

        int numS = Integer.parseInt(request.getParameter("numS"));
        String dateRep = request.getParameter("dateRep");
        List<Place> res = new ArrayList<Place>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM "
                    + "( "
                        + "SELECT * FROM LesPlaces p "
                        + "MINUS "
                        + "SELECT t.noPlace, t.noRang, p.numZ FROM LesTickets t "
                        + "INNER JOIN lesrepresentations r on t.daterep = r.daterep "
                        + "INNER JOIN lesplaces p on (t.noPlace = p.noPlace AND t.noRang = p.noRang) "
                        + "WHERE t.numS = ? AND t.daterep = ? "
                    + ") "
                    + "ORDER BY noRang";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setDate(2, new Date(Timestamp.valueOf(dateRep).getTime()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                res.add(new Place(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
        return res;
    }
}
