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
public class TraitStock extends HttpServlet {

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
            
            String date=request.getParameter("date");
            
            EnregistrementIngredient enreg=new EnregistrementIngredient();
            Connexion connex=new Connexion();
            
            String req="select\n" +
"    '1' as id,\n" +
"    qi.ingredient,\n" +
"    qi.nom,\n" +
"    sum((qi.prix-qui.prix)*(qi.quantite-qui.quantite)/1000) prix,\n" +
"	sum(qi.quantite-qui.quantite) quantite,\n" +
"    '2020/01/01' as dateEnregistrement\n" +
"from\n" +
"    (\n" +
"        select\n" +
"            '1' as id,\n" +
"            si.ingredient,\n" +
"            i.nom,\n" +
"            max(si.prix) prix,\n" +
"            sum(quantite) as quantite,\n" +
"            '2020/01/01' as dateEnregistrement\n" +
"        from\n" +
"            Stockingredient as si full join\n" +
"            Ingredient as i on si.ingredient=i.id\n" +
"        where\n" +
"            dateEntre between '1900/01/01' and '"+date+"'\n" +
"        group by\n" +
"            si.ingredient,i.nom\n" +
"    ) as qi join \n" +
"    (\n" +
"        select\n" +
"            '1' as id,\n" +
"            si.ingredient,\n" +
"            i.nom,\n" +
"            max(si.prix) prix,\n" +
"            sum(quantite) as quantite,\n" +
"            '2020/01/01' as dateEnregistrement\n" +
"        from\n" +
"            EnregistrementIngredient as si full join\n" +
"            Ingredient as i on si.ingredient=i.id\n" +
"        where\n" +
"            dateEnregistrement between '1900/01/01' and '"+date+"'\n" +
"        group by\n" +
"            si.ingredient,i.nom\n" +
"    ) as qui on qi.ingredient=qui.ingredient\n" +
"group by\n" +
"    qi.ingredient,qi.nom";
            
            System.out.println(req);
            Vector listenreg=enreg.selectV(connex.getConnexion(),req);
            EnregistrementIngredient[] listIngred=new EnregistrementIngredient[listenreg.size()];
            listenreg.copyInto(listIngred);
            
            
            
            connex.getConnexion().close();
           String page="ResteIngredient.jsp";
           request.setAttribute("liste",listIngred);
            request.setAttribute("page",page);
            
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
