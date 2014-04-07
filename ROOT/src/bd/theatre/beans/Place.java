/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.theatre.beans;

/**
 *
 * @author loic
 */
public class Place {

    private int noPlace;
    private int noRang;
    private int numZ;

    public Place(int noplace, int norang, int numz) {
        this.noPlace = noplace;
        this.noRang = norang;
        this.numZ = numz;
    }

    public int getNoPlace() {
        return this.noPlace;
    }

    public int getNoRang() {
        return this.noRang;
    }

    public int getNumZ() {
        return this.numZ;
    }

    public void setNoPlace(int noplace) {
        this.noPlace = noplace;
    }

    public void setNoRang(int norang) {
        this.noRang = norang;
    }

    public void setNumZ(int numz) {
        this.numZ = numz;
    }

}
