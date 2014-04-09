/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.core;

import bd.theatre.beans.Spectacle;
import bd.theatre.exceptions.ExceptionConnexion;
import static bd.theatre.utils.Fonctions.ecrireFichier;
import static bd.theatre.utils.Fonctions.getNomFichier;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

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

    public String addSpectacle(HttpServletRequest request, String chemin) {
        String resultat = null;
        try {
            String nomS = request.getParameter("libelle");
            String description = request.getParameter("description");
            String image = "";
            Part part = request.getPart("image");
            image = getNomFichier(part);
            if (image != null && !image.isEmpty()) {
                image = System.currentTimeMillis() + "."
                        + part.getHeader("content-type").substring(6).replace("e", "");
                /* Écriture du fichier sur le disque */
                ecrireFichier(part, image, chemin);
            }
            String requete;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = BDConnexion.getConnexion();
            requete = "SELECT MAX(numS) numS FROM LesSpectacles";
            stmt = conn.prepareStatement(requete);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int numS = rs.getInt(1) + 1;
                requete = "INSERT INTO LesSpectacle(numS, nomS, description, image) "
                            + "VALUES (?, ?, ?, ?)";
                    stmt = conn.prepareStatement(requete);
                    stmt.setInt(1, numS);
                    stmt.setString(2, nomS);
                    stmt.setString(3, description);
                    stmt.setString(4, image);
                    if (stmt.executeUpdate() > 0) {
                        resultat = "{\"error\": false, \"message\": \"Spectacle ajouté avec succès\"}";
                    } else {
                        resultat = "{\"error\": true, \"message\": \"Une erreur inattendue s'est produite lors de l'ajout\"}";
                    }
            }
        } catch (IOException ex) {
            resultat = "{\"error\": true, \"message\": \""+ ex.getMessage()+"\"}";
        } catch (ServletException ex) {
            resultat = "{\"error\": true, \"message\": \""+ ex.getMessage()+"\"}";
        } catch (ExceptionConnexion ex) {
            resultat = "{\"error\": true, \"message\": \""+ ex.getMessage()+"\"}";
        } catch (SQLException ex) {
            resultat = "{\"error\": true, \"message\": \""+ ex.getMessage()+"\"}";
        }
        return resultat;
    }
}
