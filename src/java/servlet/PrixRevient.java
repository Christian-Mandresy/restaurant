/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import base.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
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

/**
 *
 * @author ITU
 */
public class PrixRevient extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        
       
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
        try {
        HttpSession sessionClient= request.getSession();
        ServletContext context = this.getServletContext();
        Connexion connex=new Connexion();
        Produit produit=new Produit();
        System.out.println(connex.getConnexion());
        Vector listplat=produit.selectV(connex.getConnexion());
        Produit[] listprod=new Produit[listplat.size()];
       
        listplat.copyInto(listprod);
        for(int i=0;i<listprod.length;i++)
        {
            listprod[i].setConnection(connex.getConnexion());
        }
         request.setAttribute( "plat", listprod );
       
           String page="PrixRevient.jsp";
            request.setAttribute("page",page);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
            dispat.forward( request, response );
           
      
    } catch (Exception ex) {
        ex.printStackTrace();
        Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE,null,ex);
    }
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
