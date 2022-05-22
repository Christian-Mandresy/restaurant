/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import base.Postgres;
import java.sql.Date;

/**
 *
 * @author ITU
 */
public class Commande extends Postgres{
    String id;
    String tableRestau;
    Date dateCommande;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableRestau() {
        return tableRestau;
    }

    public void setTableRestau(String tableRestau) {
        this.tableRestau = tableRestau;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    
    
}
