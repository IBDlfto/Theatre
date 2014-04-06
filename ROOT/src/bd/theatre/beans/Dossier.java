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
public class Dossier {
    private int numero;
    private double montant;

    public Dossier(int numero, double montant) {
        this.numero = numero;
        this.montant = montant;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
