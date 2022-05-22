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
import model.EnregistrementIngredient;

/**
 *
 * @author ITU
 */
public class IngredientDate extends HttpServlet {

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
            
            String prix=request.getParameter("prix");
            
            EnregistrementIngredient enreg=new EnregistrementIngredient();
            Connexion connex=new Connexion();
            
            String req="select\n" +
"    '1' as id,\n" +
"    ei.ingredient,\n" +
"	i.nom,\n" +
"    sum(ei.quantite) quantite,\n" +
"    ( sum(ei.quantite)*(select avg(prix) from EnregistrementIngredient where ingredient=ei.ingredient and dateenregistrement between '"+date1+"' and '"+date2+"' )/1000) prix\n" +
"from \n" +
"    EnregistrementIngredient as ei join Ingredient as i on i.id=ei.ingredient\n" +
"where \n" +
"    ei.dateEnregistrement \n" +
"between '"+date1+"' and '"+date2+"' \n" +
"    group by i.nom,ei.ingredient order by quantite,prix";
            float total=0;
            System.out.println("valeur = "+prix);
            if(prix.equalsIgnoreCase("a"))
            {
                System.out.println("valeur = "+prix);
               req="select\n" +
"    '1' as id,\n" +
"    ei.ingredient,\n" +
"	i.nom,\n" +
"    sum(ei.quantite) quantite,\n" +
"    ( sum(ei.quantite)*(select min(prix) from EnregistrementIngredient where ingredient=ei.ingredient and dateenregistrement between '"+date1+"' and '"+date2+"' )/1000) prix\n" +
"from \n" +
"    EnregistrementIngredient as ei join Ingredient as i on i.id=ei.ingredient\n" +
"where \n" +
"    ei.dateEnregistrement \n" +
"between '"+date1+"' and '"+date2+"' \n" +
"    group by i.nom,ei.ingredient order by quantite,prix";
            }
            else if(prix.equalsIgnoreCase("r"))
            {
                System.out.println("valeur r = "+prix);
                req="select\n" +
"    '1' as id,\n" +
"    ei.ingredient,\n" +
"	i.nom,\n" +
"    sum(ei.quantite) quantite,\n" +
"    ( sum(ei.quantite)*(select max(prix) from EnregistrementIngredient where ingredient=ei.ingredient and dateenregistrement between '"+date1+"' and '"+date2+"' )/1000) prix\n" +
"from \n" +
"    EnregistrementIngredient as ei join Ingredient as i on i.id=ei.ingredient\n" +
"where \n" +
"    ei.dateEnregistrement \n" +
"between '"+date1+"' and '"+date2+"' \n" +
"    group by i.nom,ei.ingredient order by quantite,prix";
            }
            System.out.println(req);
            Vector listenreg=enreg.selectV(connex.getConnexion(),req);
            EnregistrementIngredient[] listIngred=new EnregistrementIngredient[listenreg.size()];
            listenreg.copyInto(listIngred);
            for(int i=0;i<listenreg.size();i++)
            {
                total += listIngred[i].getPrix();
            }
            
            
            connex.getConnexion().close();
           String page="IngredientListe.jsp";
           request.setAttribute("liste",listIngred);
            request.setAttribute("page",page);
            request.setAttribute("total",total);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
                dispat.forward( request, response );
        } catch (Exception ex) {
            Logger.getLogger(IngredientDate.class.getName()).log(Level.SEVERE, null, ex);
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
