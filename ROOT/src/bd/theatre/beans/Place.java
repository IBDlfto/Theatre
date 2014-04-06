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
    	private int noplace;
        private int norang;
        private int numz;
	
	public Place (int noplace, int norang,int numz ) {
		this.noplace = noplace;
		this.norang = norang;
                this.numz = numz;
	}

	public int getNoPlace () {
		return this.noplace;
	}
	
	public float getNoRang () {
		return this.norang;
	}
        
        public float getNumZ () {
		return this.numz;
	}
	
	public void setNoPlace (int noplace) {
		this.noplace = noplace;
	}
	
	public void setNoRang (int norang) {
		this.norang = norang;
	}
             
        public void setNumZ (int numz) {
		this.numz = numz;
	}
        
}
