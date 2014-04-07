/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.exceptions.ExceptionConnexion;
import bd.theatre.exceptions.TicketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fagno
 */
public class BDTickets {

    public String error;

    public String reserver(HttpServletRequest request)
            throws TicketException, ExceptionConnexion {

        String message = null;
        int numS = Integer.parseInt(request.getParameter("numS"));
        String dateRep = request.getParameter("dateRep");
        int noPlace = Integer.parseInt(request.getParameter("noPlace"));
        int noRang = Integer.parseInt(request.getParameter("noRang"));
        int numZ = Integer.parseInt(request.getParameter("numZ"));

        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT MAX(noSerie) noSerie FROM LesTickets";
            stmt = conn.prepareStatement(requete);
            rs = stmt.executeQuery();
            int noSerie = rs.getInt(1) + 1;

            requete = "INSERT INTO Lestickets(numS, dateRep, noPlace, noRang, dateEmission, noSerie) "
                    + "VALUES (?, ?, ?, ?, NOW(), ?)";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setTimestamp(2, Timestamp.valueOf(dateRep));
            stmt.setInt(3, noPlace);
            stmt.setInt(4, noRang);
            stmt.setInt(5, noSerie);
            if (stmt.executeUpdate() > 0) {
                requete = "SELECT p.noPlace, p.noRang, c.prix FROM LesPlaces p "
                        + "INNER JOIN LesZones z ON p.numZ = z.numZ "
                        + "INNER JOIN LesCategories c ON z.nomC = c.nomC "
                        + "WHERE z.numZ = ? AND noPlace = ? AND noRang = ?";
                stmt = conn.prepareStatement(requete);
                stmt.setInt(1, numZ);
                stmt.setInt(2, noPlace);
                stmt.setInt(3, noRang);
                rs = stmt.executeQuery(requete);
                if (rs.next()) {
                    message = "{\"error\": false, \"noPlace\": \"" + rs.getInt(1) + "\","
                            + " \"noRang\": \"" + rs.getInt(2) + "\", \"prix\": \"" + rs.getInt(3) + "\"}";
                } else {
                    message = "{\"error\": true, \"message\": \"Une erreur inattendue s'est produite\"}";
                }
            } else {
                message = "{\"error\": true, \"message\": \"Une erreur s'est produite lors de l'ajout\"}";
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
            throw new TicketException(" Problème dans l'insertion des Tickets.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
        return message;
    }

    public void supprimer(int numS, String dateRep, int noPlace, int noRang, int numZ) {
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            requete = "DELETE LesTickets WHERE numS = ? AND dateRep = ? "
                    + "AND noPlace = ? AND noRang = ? AND numZ = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setTimestamp(2, Timestamp.valueOf(dateRep));
            stmt.setInt(3, noPlace);
            stmt.setInt(4, noRang);
            stmt.setInt(5, numZ);
            stmt.executeUpdate();
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            requete = "SELECT MAX(noSerie) noSerie FROM LesTickets";
            stmt = conn.prepareStatement(requete);
            rs = stmt.executeQuery();
            System.out.println(rs.getInt(1) + 1);
        } catch (ExceptionConnexion ex) {
            Logger.getLogger(BDTickets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BDTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
