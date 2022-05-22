/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import base.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
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
import model.VuePaiement;

/**
 *
 * @author ITU
 */
public class TraitListPaie extends HttpServlet {

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
            String date1=request.getParameter("date1");
            String date2=request.getParameter("date2");
            String type=request.getParameter("type");
            
            Connexion connex=new Connexion();
            VuePaiement vue=new VuePaiement();
            String req="select nomClient,tablerestau as table,datepaiement,(montant-retour)as montant ,nomTypePaiement "
                    + "from vuePaiement where datePaiement between '"+date1+"' and '"+date2+"' and idtypepaiement='"+type+"' order by datePaiement";
            
            System.out.println(req);
            
            Vector listvuepaye=vue.selectV(connex.getConnexion(),req);
            VuePaiement[] listvue=new VuePaiement[listvuepaye.size()];
            listvuepaye.copyInto(listvue);
            float total=0;
            Statement stmt=connex.getConnexion().createStatement();
            req="select sum(montant-retour) from vuePaiement where datePaiement between '"+date1+"' and '"+date2+"' and idtypepaiement='"+type+"' group by idtypepaiement";
            ResultSet res=stmt.executeQuery(req);
            System.out.println(req);
            
            while(res.next())
            {
                total=res.getFloat(1);
            }
            
            
            request.setAttribute("liste", listvue);
            request.setAttribute("total", total);
            
            stmt.close();
            res.close();
            connex.getConnexion().close();
           String page="ListePayement.jsp";
            request.setAttribute("page",page);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
                dispat.forward( request, response );
            
        } catch (Exception ex) {
            Logger.getLogger(TraitListPaie.class.getName()).log(Level.SEVERE, null, ex);
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
