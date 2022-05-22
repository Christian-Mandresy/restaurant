/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alicia
 */
public class Connexion {
    Connection connection;

    public Connection getConnexion() {
        return connection;
    }

    public void setConnexion(Connection connection) {
        this.connection = connection;
    }
    
    public Connexion(){
        try{
            Class.forName("org.postgresql.Driver");
            this.setConnexion(DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant","postgres","mdpprom13"));
            /*
                5432 => port postgres
                taxiBrousse => database
                postgres => user
                admin => motdepasse
            */
            this.getConnexion().setAutoCommit(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try{
            this.getConnexion().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void rollback(){
        try{
            this.getConnexion().rollback();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void commit(){
        try{
            this.getConnexion().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
