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
public class Commandeserveur extends Postgres{
    String id;
    String idserveur;
    String idcommande;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdserveur() {
        return idserveur;
    }

    public void setIdserveur(String idserveur) {
        this.idserveur = idserveur;
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }
}
