/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd.theatre.beans;

/**
 *
 * @author toure
 */
public class Representation {
    private int numS;
    private String date;
    private String dateF;

    public Representation(int num, String date) {
        this.numS = num;
        this.date = date;
    }

    public int getNumS() {
        return numS;
    }

    public void setNumS(int num) {
        this.numS = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }
}
