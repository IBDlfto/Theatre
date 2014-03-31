package bd.theatre.core;

import bd.theatre.beans.Categorie;
import bd.theatre.exceptions.CategorieException;
import bd.theatre.exceptions.ExceptionConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDCategories {

    public BDCategories() {

    }

    public ArrayList<Categorie> getCategorie()
            throws CategorieException, ExceptionConnexion {
        ArrayList<Categorie> res = new ArrayList<Categorie>();
        String requete;
        Statement stmt;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion();

        requete = "select nomc, prix from LesCategories order by nomc";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.add(new Categorie(rs.getString(1), rs.getFloat(2)));
            }
        } catch (SQLException e) {
            throw new CategorieException(" Problème dans l'interrogation des catégories.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }
        BDConnexion.FermerTout(conn, stmt, rs);
        return res;
    }
}
