/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd.theatre.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author toure
 */
public class Fonctions {
    
    public static String formatter(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMMM yyyy 'à' hh:mm", Locale.FRANCE);
        return df.format(dt);
    }
    
    public static String format(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(dt);
    }
    
    public static String getCookieValue(HttpServletRequest request, String nom) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && nom.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
        Cookie cookie = new Cookie(nom, valeur);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/public/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
    
    public static String getNomFichier(Part part) {
        /* Boucle sur chacun des paramètres de l'en-tête "contentdisposition".
         */
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            /* Recherche de l'éventuelle présence du paramètre "filename".
             */
            if (contentDisposition.trim().startsWith("filename")) {
                /* Si "filename" est présent, alors renvoi de sa valeur,
                 c'est-à-dire du nom de fichier. */
                return contentDisposition.substring(
                        contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }

    public static void ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
        int TAILLE_TAMPON = 10240; // 10 ko
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
}
