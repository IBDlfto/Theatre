/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Spectacle;
import bd.theatre.beans.Utilisateur;
import bd.theatre.exceptions.ExceptionConnexion;
import bd.theatre.exceptions.SpectacleException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author toure
 */
public class BDSpectacles {

    public BDSpectacles() {

    }

    public Vector<Spectacle> getSpectacle()
            throws SpectacleException, ExceptionConnexion {
        Vector<Spectacle> res = new Vector<Spectacle>();
        String requete;
        Statement stmt;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion();

        requete = "select * from LesSpectacles";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.addElement(new Spectacle(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            throw new SpectacleException(" Problème dans l'interrogation des spéctacles.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }
        BDConnexion.FermerTout(conn, stmt, rs);
        return res;
    }
}
