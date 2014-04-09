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

    public static String error;

    public static int addDossier(int montant)
            throws DossierException, ExceptionConnexion {
        int noDossier = 0;
        try {
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();

            requete = "SELECT MAX(noDossier) noDossier FROM LesDossiers";
            stmt = conn.prepareStatement(requete);
            rs = stmt.executeQuery();
            if (rs.next()) {
                noDossier = rs.getInt(1) + 1;
                System.out.println(noDossier);
                requete = "INSERT INTO LesDossiers VALUES(? ,?)";
                stmt = conn.prepareStatement(requete);
                stmt.setInt(1, noDossier);
                stmt.setInt(2, montant);
                rs = stmt.executeQuery();
            }
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
        return noDossier;
    }
}
