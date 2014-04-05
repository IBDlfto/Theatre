/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.exceptions;

import java.sql.SQLException;

/**
 *
 * @author toure
 */
public class SpectacleException extends SQLException {

    public SpectacleException() {
        // TODO Auto-generated constructor stub
    }

    public SpectacleException(String m) {
        super(m);
    }
}
