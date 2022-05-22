/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import base.Connexion;
import base.Postgres;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITU
 */
public class DetailsCommande extends Postgres{
    String id;
    String commande;
    String produit;
    int quantite;
    float prix;
    float depense;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getDepense() {
        return depense;
    }

    public void setDepense(float depense) {
        this.depense = depense;
    }
    
    public DetailsCommande[] ListACuisiner(DetailsCommande[] list)
    {
        try {
           
          
            Vector valiny=new Vector();
            for(int i=0;i<list.length;i++)
            {
                if(list[i].getQuantite()>1)
                {
                    for(int ii=0;ii<list[i].getQuantite();ii++)
                    {
                        DetailsCommande detail=new DetailsCommande();
                        detail.setId(list[i].getId());
                        detail.setCommande(list[i].getCommande());
                        detail.setProduit(list[i].getProduit());
                        detail.setPrix(list[i].getPrix());
                        detail.setDepense(list[i].getDepense());
                        detail.setQuantite(1);
                        valiny.add(detail);
                    }
                }
                else
                {
                    valiny.add(list[i]);
                }
        
            }
            DetailsCommande[] val=new DetailsCommande[valiny.size()];
            valiny.copyInto(val);
            return val;
        } catch (Exception ex) {
            Logger.getLogger(DetailsCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
