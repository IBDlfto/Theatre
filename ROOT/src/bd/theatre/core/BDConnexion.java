package bd.theatre.core;

import java.sql.*;
import jus.util.IO;
import bd.theatre.exceptions.ExceptionConnexion;

public final class BDConnexion {

    public BDConnexion() {

    }

    /**
     * Obtenir une nouvelle connexion a la BD, en fonction des parametres
     * contenus dans un fichier de configuration.
     *
     * @return une nouvelle connexion a la BD
     * @throws ExceptionConnexion si la connexion a echoue
     */
    public static Connection getConnexion() throws ExceptionConnexion {
        Connection conn = null;
        try {
            String login = "toureab";
            String mdp = "bd2013";
            String url = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima";
            String driver = "oracle.jdbc.OracleDriver";

            Class.forName(driver);
            // hopper@UFR, Oracle
            conn = DriverManager.getConnection(url, login, mdp);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            IO.afficherln("Connexion impossible : " + e.getMessage());// handle any errors
            IO.afficherln("SQLException: " + e.getMessage());
            IO.afficherln("SQLState: " + e.getSQLState());
            IO.afficherln("VendorError: " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            throw new ExceptionConnexion("problème d'identification du pilote \n" + e.getMessage());
        }
        return conn;
    }

    /**
     * Fermer la connexion, l'instruction et la structure de resultats. Fermer
     * les 3 a la fois semble correspondre a de nombreux cas.
     *
     * @param conn la connexion
     * @param stmt l'instruction
     * @param rs la structure de resultats
     */
    public static void FermerTout(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;
        }
    }
    
    public static void FermerTout(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;
        }
    }
}
