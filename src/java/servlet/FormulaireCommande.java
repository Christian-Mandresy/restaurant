/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import base.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Produit;
import model.Serveur;
import model.TableRestau;

/**
 *
 * @author ITU
 */
public class FormulaireCommande extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            HttpSession sessionClient= request.getSession();
            ServletContext context = this.getServletContext();
            Produit produit=new Produit();
            produit.setFini(1);
            Serveur serveur=new Serveur();
       
            TableRestau table=new TableRestau();
            Connexion connex=new Connexion();
            Vector listtable=table.selectV(connex.getConnexion());
            TableRestau[] listtab=new TableRestau[listtable.size()];
            listtable.copyInto(listtab);
            Vector listproduit;
            listproduit = produit.selectV(connex.getConnexion());
            Produit[] listprod=new Produit[listproduit.size()];
            listproduit.copyInto(listprod);
            Vector lisserveur=serveur.selectV(connex.getConnexion());
            Serveur[] listserv=new Serveur[lisserveur.size()];
            lisserveur.copyInto(listserv);
            
            request.setAttribute( "produit", listprod );
            request.setAttribute( "table", listtab );
            request.setAttribute( "serveur", listserv );
    
            connex.getConnexion().close();
            String page="FormCommande.jsp";
            request.setAttribute("page",page);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
                dispat.forward( request, response );
        } catch (Exception ex) {
            Logger.getLogger(FormulaireCommande.class.getName()).log(Level.SEVERE, null, ex);
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
