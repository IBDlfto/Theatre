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
public class Spectacle {

    private int id;
    private String nom;
    private String image;

    public Spectacle(int id, String nom, String image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
