/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd.theatre.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author toure
 */
public class Fonctions {
    
    public static String formatter(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMMM yyyy 'à' hh:mm");
        return df.format(dt);
    }
}
