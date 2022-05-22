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
import java.sql.SQLException;
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
import model.CommandeNPayer;

/**
 *
 * @author ITU
 */
public class CommandeNonPayer extends HttpServlet {

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
            
            Connexion connex=new Connexion();
            Statement stmt=connex.getConnexion().createStatement();
            Vector idcommande=new Vector();
            
            ResultSet res=stmt.executeQuery("select id from paiementcommande where datepaiement between '"+date1+"' and '"+date2+"'");
            System.out.println("select id from paiementcommande where datepaiement between '"+date1+"' and '"+date2+"'");
            while(res.next())
            {
                idcommande.add(res.getString(1));
            }
            CommandeNPayer[] list=new CommandeNPayer[idcommande.size()];
            
            res.close();
            stmt.close();
            
            Statement stmt2=connex.getConnexion().createStatement();
            ResultSet res2=stmt2.executeQuery("select 1");
            for(int i=0;i<idcommande.size();i++)
            {
                String req2="select c.tableRestau,coalesce(pc.nomClient,'') as nomClient,(select sum(prixIngredient) from (select \n" +
"	c.id commande,\n" +
"	qip.produit nomProduit,\n" +
"	qip.ingredient nomIngredient,\n" +
"	sum(qip.prix*dc.quantite) prixIngredient\n" +
"from \n" +
"	qteIngredientProduit as qip join detailsCommande as dc on qip.idproduit=dc.produit\n" +
"	join Commande as cc on dc.commande=c.id\n" +
"where \n" +
"	cc.id=c.id\n" +
"group by \n" +
"	qip.produit,qip.ingredient,c.id\n" +
"union\n" +
"select \n" +
"	c.id commande,\n" +
"	p.nom nomProduit,\n" +
"	'' as nomIngredient,\n" +
"	sum(p.prix*dc.quantite) prixIngredient\n" +
"from \n" +
"	produit as p join detailsCommande as dc on dc.produit=p.id\n" +
"	join Commande as cc on dc.commande=c.id\n" +
"where\n" +
"	p.fini=1 and\n" +
"	cc.id=c.id\n" +
"group by\n" +
"	p.nom,c.id) as a) as prixCommande,coalesce((select sum(montant-retour) as prixIngredient from PaiementCommande as pc where pc.commande=c.id),0) as paiement from Commande as c left join paiementCommande as pc on pc.commande=c.id where c.id='"+(String)idcommande.elementAt(i)+"' group by c.tableRestau,nomClient,c.id;\n" +
"";
                System.out.println(req2);
                res2=stmt2.executeQuery(req2);
                

            while(res2.next())
            {
                list[i].setTable(res2.getString(1));
                list[i].setNomClient(res2.getString(2));
                list[i].setTotal(res2.getFloat(3));
                list[i].setPayer(res2.getFloat(4));
                list[i].setRestant(res2.getFloat(3)-res2.getFloat(4));
            }

            }
            
            request.setAttribute("liste", list);
            
            
            res2.close();
            stmt2.close();
            connex.getConnexion().close();
           String page="ListeCommandeNonPayer.jsp";
            request.setAttribute("page",page);
            RequestDispatcher dispat = context.getRequestDispatcher( "/Template.jsp" );
                dispat.forward( request, response );
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeNonPayer.class.getName()).log(Level.SEVERE, null, ex);
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
