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
public class QteIngredientProduit extends Postgres{
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    String Produit;
    String Ingredient;
    float Qte;
    float Prix;

    public String getProduit() {
        return Produit;
    }

    public void setProduit(String Produit) {
        this.Produit = Produit;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String Ingredient) {
        this.Ingredient = Ingredient;
    }


    public float getQte() {
        return Qte;
    }

    public void setQte(float qte) {
        this.Qte = qte;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float prix) {
        this.Prix = prix;
    }
    
    
}
