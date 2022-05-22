/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import base.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Commande;
import model.Commandeserveur;
import model.DetailsCommande;
import model.Produit;

/**
 *
 * @author ITU
 */
public class TraitCommande extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession sessionClient= request.getSession();
            ServletContext context = this.getServletContext();
            String idServeur=request.getParameter("serveur");
            String idTable=request.getParameter("table");
            Date daty=new Date(System.currentTimeMillis());
            java.util.Date jud=new java.util.Date(System.currentTimeMillis());
            int minute=jud.getMinutes();
            int second=jud.getSeconds();
            int hr=jud.getHours();
           
            Produit produit=new Produit();
            Connexion connex=new Connexion(); 
            Vector listproduit=produit.selectV(connex.getConnexion());
            
            Produit[] listprod=new Produit[listproduit.size()];
            listproduit.copyInto(listprod);
            Vector idproduits=new Vector();
            Vector qte=new Vector();
            Vector prix=new Vector();
            
           
            for(int i=0;i<listprod.length;i++)
            {
                if(!request.getParameter("qte"+i).equals("0"))
                {
                    idproduits.add(i+1);
                    qte.add((String)request.getParameter("qte"+i));
                    prix.add(request.getParameter("prix"+i));
                }  
            }

            int alava=idproduits.size();

            if(alava>0){
                
                String ilayizy="";

                Statement stt=connex.getConnexion().createStatement();
                String sql="insert into Commande values(nextval('seqCommande'),'"+idTable+"','"+
                        new Integer(daty.getYear()+1900).toString()+"-"+new Integer(daty.getMonth()+1).toString()+"-"+
                        new Integer(daty.getDate()).toString()+" "+new Integer(hr).toString()+":"+new Integer(minute).toString()+":"+
                        new Integer(second).toString()+"')";
                int ressss=stt.executeUpdate(sql);
                
                
                Statement sttt=connex.getConnexion().createStatement();
                ResultSet ress=sttt.executeQuery("select id from commande order by datecommande desc limit 1");
                boolean bbb=ress.next();
                ilayizy=ress.getString(1);
                stt.close(); ress.close();
                
                for(int i=0;i<idproduits.size();i++)
                {
                    DetailsCommande detail=new DetailsCommande();
                    detail.setCommande(ilayizy);
                    detail.setProduit(idproduits.get(i).toString() );
                    detail.setQuantite(Integer.parseInt((String)qte.get(i)));
                    detail.setPrix(Float.parseFloat((String)prix.get(i)));
                    detail.setDepense(Float.parseFloat((String)prix.get(i))/2);
                    detail.insertSeq(connex.getConnexion());
                }
                
                Commandeserveur comser=new Commandeserveur();
                comser.setIdcommande(ilayizy); comser.setIdserveur(idServeur);
                comser.insertSeq(connex.getConnexion());
                
            }

            connex.getConnexion().close();
            request.setAttribute("success","");
            
            RequestDispatcher dispat = context.getRequestDispatcher( "/ChoixTable" );
                dispat.forward( request, response );
            
        } catch (Exception ex) {
            Logger.getLogger(TraitCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
