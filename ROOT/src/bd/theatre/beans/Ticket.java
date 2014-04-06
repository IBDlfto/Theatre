/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd.theatre.beans;

import java.sql.Date;

/**
 *
 * @author loic
 */
public class Ticket {
    
      //  private Dossier dossier;
      //  private Place place;
        
        private int noserie;
        private int numS;
    	private String daterep;
        private int noplace;
        private int norang;
        private String datemiss;
        private int nodossier;
                             
	public Ticket ( int noserie, int numS, String daterep, int noplace, int norang, String datemiss, int nodossier ) {
            
             this.noserie =noserie;
             this.numS = numS;
             this.daterep=daterep;
             this.noplace =noplace;
             this.norang = norang;
             this.datemiss = datemiss;
             this.nodossier= nodossier;
	}

        public int getNoserie() {
            return noserie;
        }

        public void setNoserie(int noserie) {
            this.noserie = noserie;
        }

        public int getNumS() {
            return numS;
        }

        public void setNumS(int numS) {
            this.numS = numS;
        }

        public String getDaterep() {
            return daterep;
        }

        public void setDaterep(String daterep) {
            this.daterep = daterep;
        }

        public int getNoplace() {
            return noplace;
        }

        public void setNoplace(int noplace) {
            this.noplace = noplace;
        }

        public int getNorang() {
            return norang;
        }

        public void setNorang(int norang) {
            this.norang = norang;
        }

        public String getDatemiss() {
            return datemiss;
        }

        public void setDatemiss(String datemiss) {
            this.datemiss = datemiss;
        }

        public int getNodossier() {
            return nodossier;
        }

        public void setNodossier(int nodossier) {
            this.nodossier = nodossier;
        }

}
