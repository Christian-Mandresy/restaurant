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
public class Fini extends Postgres{
    String id;
    String produit;
    int quantite;
    String detailCommande;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDetailCommande() {
        return detailCommande;
    }

    public void setDetailCommande(String detailCommande) {
        this.detailCommande = detailCommande;
    }
    
    
    
    
}
