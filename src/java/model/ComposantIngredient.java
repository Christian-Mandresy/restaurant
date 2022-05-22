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
public class ComposantIngredient extends Postgres{
    String IdComposantIngredient;
    String IdComposant;
    String NomComposant;
    String IdIngredient;
    String NomIngredient;
    float Quantite;

    public String getIdComposantIngredient() {
        return IdComposantIngredient;
    }

    public void setIdComposantIngredient(String IdComposantIngredient) {
        this.IdComposantIngredient = IdComposantIngredient;
    }

    public String getIdComposant() {
        return IdComposant;
    }

    public void setIdComposant(String IdComposant) {
        this.IdComposant = IdComposant;
    }

    public String getNomComposant() {
        return NomComposant;
    }

    public void setNomComposant(String NomComposant) {
        this.NomComposant = NomComposant;
    }

    public String getIdIngredient() {
        return IdIngredient;
    }

    public void setIdIngredient(String IdIngredient) {
        this.IdIngredient = IdIngredient;
    }

    public String getNomIngredient() {
        return NomIngredient;
    }

    public void setNomIngredient(String NomIngredient) {
        this.NomIngredient = NomIngredient;
    }

    public float getQuantite() {
        return Quantite;
    }

    public void setQuantite(float quantite) {
        this.Quantite = quantite;
    }
    
    
}
