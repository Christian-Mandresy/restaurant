/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author ITU
 */
public class StockIngredient {
    String Id;
    String Ingredient;
    float Qte;
    Date DateEntre;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public void setQte(float Qte) {
        this.Qte = Qte;
    }

    public Date getDateEntre() {
        return DateEntre;
    }

    public void setDateEntre(Date DateEntre) {
        this.DateEntre = DateEntre;
    }
    
    
}
