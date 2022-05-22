/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import base.Postgres;
import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITU
 */
public class Pourboire extends Postgres{
    String idServeur;
    String serveur;
    String commande;
    float prix;
    Date datecommande;

    public float total(Connection con,String dt1,String dt2){

        float ret=0;

        try {
            this.filtre();
        } catch (Exception ex) {
            Logger.getLogger(Pourboire.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vector temp;
        try {
            String sql="select * from pourboire where datecommande>="+dt1+" and datecommande<="+dt2+" and idserveur='"+this.idServeur+"'";
            temp = this.selectV(con,sql);
            System.out.println(sql);
            Pourboire[] pour=new Pourboire[temp.size()]; temp.copyInto(pour);
            for(int i=0;i<pour.length;i++){
            ret+=pour[i].getPrix()*0.02;
        }
        } catch (Exception ex) {
            Logger.getLogger(Pourboire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        return ret;
    }

    public String getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(String idServeur) {
        this.idServeur = idServeur;
    }

    public String getServeur() {
        return serveur;
    }

    public void setServeur(String serveur) {
        this.serveur = serveur;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }
    
    
    
}
