/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Zone;
import bd.theatre.exceptions.ZoneException;
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
 * @author Fagno
 */
public class BDZones {

    public String error;

     public List<Zone> getZones(HttpServletRequest request)
     throws ZoneException, ExceptionConnexion {

        int numz = Integer.parseInt(request.getParameter("numz"));
        List<Zone> res = new ArrayList<Zone>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM LesZones WHERE numz = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numz);
            rs = stmt.executeQuery();
            while (rs.next()) {
                res.add(new Zone(rs.getInt(1), rs.getString(2)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            throw new ZoneException(" Problème dans l'interrogation des Zones.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
        return res;
    }
    
    public boolean addZones(HttpServletRequest request)
     throws ZoneException, ExceptionConnexion{
         
         int numz = Integer.parseInt(request.getParameter("numz"));
         String nomc = request.getParameter("nomc");

          try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            
            requete = "INSERT INTO Leszones ('?','?')";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, numz);
            stmt.setString(2, nomc);
            rs = stmt.executeQuery();
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
            throw new ZoneException(" Problème dans l'insertion des Zones.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
          return true;
     }

     
    
}