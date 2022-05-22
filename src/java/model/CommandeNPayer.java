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
public class CommandeNPayer extends Postgres{
    String table;
    String nomClient;
    float total;
    float payer;
    float restant;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPayer() {
        return payer;
    }

    public void setPayer(float payer) {
        this.payer = payer;
    }

    public float getRestant() {
        return restant;
    }

    public void setRestant(float restant) {
        this.restant = restant;
    }
    
    
    
}
