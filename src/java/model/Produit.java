/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import base.Postgres;

/**
 *
 * @author ITU
 */
public class Produit extends Postgres{
    private String id;
    String typeproduit;
    private String Nom;
    private float prix;
    private int fini;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    

    public int getFini() {
        return fini;
    }

    public void setFini(int fini) {
        this.fini = fini;
    }
    
    
}
