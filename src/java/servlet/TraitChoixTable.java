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
import model.Commande;
import model.DetailsCommande;
import model.TableRestau;

/**
 *
 * @author ITU
 */
public class TraitChoixTable extends HttpServlet {

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
            
            String idtable=request.getParameter("table");
            
            
            Connexion connex=new Connexion();
            Commande comm=new Commande();
            
            String sqlsql="select * from Commande where tableRestau='"+idtable+"'"+" And date(datecommande) = date(now())";
            System.out.println(sqlsql);
           Vector listcommande=comm.selectV(connex.getConnexion(),sqlsql);
           Commande[] listcom=new Commande[listcommande.size()];
           listcommande.copyInto(listcom);
           TableRestau tabla=new TableRestau();
           Vector listtable=tabla.selectV(connex.getConnexion());
           TableRestau[] listtabla=new TableRestau[listtable.size()];
           listtable.copyInto(listtabla);
           
            connex.getConnexion().close();
           String page="ListCommande.jsp";
           request.setAttribute("listcommande",listcom);
           request.setAttribute("listtable",listtabla);
            request.setAttribute("page",page);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
                dispat.forward( request, response );
           
            
        }catch (Exception ex) {
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
