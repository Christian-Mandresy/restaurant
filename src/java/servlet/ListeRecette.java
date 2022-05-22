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

import model.Commandecomplet;
import model.Produit;
import model.QteIngredientProduit;
import model.Serveur;

/**
 *
 * @author ITU
 */
public class ListeRecette extends HttpServlet {
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


        String id=request.getParameter("id");

        Connexion connex=new Connexion();
        QteIngredientProduit prod=new QteIngredientProduit(); prod.setId(id); prod.setFiltreId(); prod.setAfficher(true);
        Vector vec=new Vector();
        vec=prod.selectV(connex.getConnexion(),"select id,produit,ingredient,sum(quantite),sum(prix) from qteingredientproduit where id='"+id+"' group by id,produit,ingredient");

        Produit p=new Produit();
        p.setId(id);
        p.setFiltreId();
        p=(Produit)p.selectOne(connex.getConnexion());

        QteIngredientProduit[] liste=new QteIngredientProduit[vec.size()];
        vec.copyInto(liste);
        request.setAttribute("liste",liste);
        request.setAttribute("produit",p);
        connex.getConnexion().close();

        RequestDispatcher dispat = context.getRequestDispatcher( "/listerecette.jsp");
            dispat.forward( request, response );
           
      
    } catch (Exception ex) {
        ex.printStackTrace();
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
