/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.exceptions;

import java.sql.SQLException;

/**
 *
 * @author loic
 */
public class DossierException extends SQLException {

    public DossierException() {
        // TODO Auto-generated constructor stub
    }

    public DossierException(String m) {
        super(m);
    }
}
