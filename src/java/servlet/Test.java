/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import base.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailsCommande;

/**
 *
 * @author ITU
 */
public class Test extends HttpServlet {

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
            Connexion connex=new Connexion();
            DetailsCommande detail=new DetailsCommande();
            Vector list=detail.selectV(connex.getConnexion());
            DetailsCommande[] listdetail=new DetailsCommande[list.size()];
            list.copyInto(listdetail);
            DetailsCommande[] listvaliny=detail.ListACuisiner(listdetail);
            for(int i=0;i<listvaliny.length;i++)
            {
                /*try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.print(" ");
                out.print("id"+listvaliny[i].getId());
                out.print("commande"+listvaliny[i].getCommande());
                out.print("prix"+listvaliny[i].getPrix());
                out.print("condition"+listvaliny[i].getCondition());
                out.print("produit"+listvaliny[i].getProduit());
                out.print("quantite"+listvaliny[i].getQuantite());
                out.print(" ");
                /*}*/
            }   
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
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
