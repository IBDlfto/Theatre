/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Dossier;
import bd.theatre.exceptions.DossierException;
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
public class BDDossiers {

    public String error;

     public List<Dossier> getDossiers(HttpServletRequest request)
     throws DossierException, ExceptionConnexion {

        int nodossier = Integer.parseInt(request.getParameter("nodossier"));
        List<Dossier> res = new ArrayList<Dossier>();
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT * FROM Lesdossiers WHERE nodossier = ?";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, nodossier);
            rs = stmt.executeQuery();
            while (rs.next()) {
                res.add(new Dossier(rs.getInt(1), rs.getInt(2)));
            }
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            throw new DossierException(" Problème dans l'interrogation des dossiers.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
        return res;
    }
    
    public boolean addDossiers(HttpServletRequest request)
     throws DossierException, ExceptionConnexion{
         
         int nodossier = Integer.parseInt(request.getParameter("nodossier"));
         int montant = Integer.parseInt(request.getParameter("montant"));

          try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            
            requete = "INSERT INTO LesDossiers ('?','?')";
            stmt = conn.prepareStatement(requete);
            stmt.setInt(1, nodossier);
            stmt.setInt(2, montant);
            rs = stmt.executeQuery();
            BDConnexion.FermerTout(conn, stmt, rs);
        } catch (SQLException e) {
            error = e.getMessage();
            throw new DossierException(" Problème dans l'insertion des dossiers.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        } catch (ExceptionConnexion ex) {
            error = ex.getMessage();
            throw new ExceptionConnexion(ex);
        }
          return true;
     }

     
    
}