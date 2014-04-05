/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Spectacle;
import bd.theatre.exceptions.ExceptionConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toure
 */
public class BDSpectacles {

    public String error;

    public BDSpectacles() {

    }

    public List<Spectacle> getSpectacle() {
        List<Spectacle> res = new ArrayList<Spectacle>();
        try {
            String requete;
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "select * from LesSpectacles";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.add(new Spectacle(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
        }
        return res;
    }
    
    public static void main(String[] args) throws ExceptionConnexion {
        BDConnexion.getConnexion();
    }
}
