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
public class Zone {
    
    	private int numz;
	private String nomc;
	
	public Zone (int numz, String nomc) {
		this.numz = numz;
		this.nomc = nomc;
	}

    public int getNumz() {
        return numz;
    }

    public void setNumz(int numz) {
        this.numz = numz;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }


}
