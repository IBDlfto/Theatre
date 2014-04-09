/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import static bd.theatre.core.BDDossiers.addDossier;
import bd.theatre.exceptions.ExceptionConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Fagno
 */
public class BDTickets {

    public String error;

    public String allInOne(List<String[]> panier, int montant) {
        String resultat = null;
        try {
            Connection conn = BDConnexion.getConnexion();
            conn.commit();
            int noDossier = addDossier(montant);
            BDTickets form = new BDTickets();

            for (String[] p : panier) {
                int numS = Integer.parseInt(p[0]);
                String nom = p[1];
                String dateRep = p[2];
                int noPlace = Integer.parseInt(p[3]);
                int noRang = Integer.parseInt(p[4]);
                
                String res = form.reserver(numS, dateRep, noPlace, noRang, noDossier, nom);
                if (res.indexOf("n'est") > -1 || form.error != null) {
                    if (form.error != null) {
                        res = form.error;
                    }
                    resultat = "{\"error\": true, \"message\": \"" + res + "\"}";
                    conn.rollback();
                    break;
                }
            }
        } catch (ExceptionConnexion ex) {
            resultat = "{\"error\": true, \"message\": \"" + ex.getMessage() + "\"}";
        } catch (SQLException ex) {
            resultat = "{\"error\": true, \"message\": \"" + ex.getMessage() + "\"}";
        }
        if (resultat == null) {
            resultat = "{\"error\": false, \"message\": \"Votre achat a été effectué avec succès\"}";
        }
        return resultat;
    }

    public String reserver(int numS, String dateRep, int noPlace, int noRang, int noDossier, String nom) {
        String resultat = null;
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            requete = "SELECT * FROM LesTickets WHERE numS = ? AND dateRep = ? AND noPlace = ? AND noRang = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numS);
            stmt.setDate(2, new Date(Timestamp.valueOf(dateRep).getTime()));
            stmt.setInt(3, noPlace);
            stmt.setInt(4, noRang);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("OK");

                requete = "SELECT MAX(noSerie) noSerie FROM LesTickets";
                stmt = conn.prepareStatement(requete);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    int noSerie = rs.getInt(1) + 1;

                    requete = "INSERT INTO Lestickets(numS, dateRep, noPlace, noRang, dateEmission, noSerie, noDossier) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    stmt = conn.prepareStatement(requete);
                    stmt.setInt(1, numS);
                    stmt.setDate(2, new Date((Timestamp.valueOf(dateRep)).getTime()));
                    stmt.setInt(3, noPlace);
                    stmt.setInt(4, noRang);
                    stmt.setDate(5, new Date((new java.util.Date()).getTime()));
                    stmt.setInt(6, noSerie);
                    stmt.setInt(7, noDossier);
                    if (stmt.executeUpdate() > 0) {
                        System.out.println("OKOK");
                        resultat = "OK";
                    } else {
                        conn.rollback();
                        resultat = "La place: " + noPlace + " au rang: " + noRang + " du spectacle :"
                                + nom + " pour la date: " + dateRep + " n'est plus disponible";
                    }
                }
            } else {
                conn.rollback();
                System.out.println("!");
                resultat = "La place: " + noPlace + " au rang: " + noRang + " du spectacle :"
                        + nom + " pour la date: " + dateRep + " n'est plus disponible";
            }

            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
        return resultat;
    }
}
