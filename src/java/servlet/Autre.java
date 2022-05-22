// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package servlet;

// import base.Connexion;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Vector;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletContext;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import model.Commandecomplet;
// import model.Produit;
// import model.Serveur;
// import model.TypeProduit;

// /**
//  *
//  * @author ITU
//  */
// public class Autre extends HttpServlet {

//     /**
//      * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//      * methods.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         try {
//         HttpSession sessionClient= request.getSession();
//         ServletContext context = this.getServletContext();
//         Connexion connex=new Connexion();
//         Serveur serveur=new Serveur();

//         String idS=request.getParameter("serveur");
//         String date=request.getParameter("date");

//         Commandecomplet cc=new Commandecomplet();
//         cc.setServeur(serveur);
//         cc.setDatecommande(date);
//         int[] f={0,0,0,0,0,0,1,0,1};
//         cc.filtre(f);
//         Vector vec=cc.selectV(connex.getConnexion());
//         Commandecomplet[] liste=new Commandecomplet[vec.size()];
//         vec.copyInto(liste);

//         request.setAttribute("serveur",idS);
//         request.setAttribute("date",date);
//         request.setAttribute("liste",idS);

//         RequestDispatcher dispat = context.getRequestDispatcher( "/autre.jsp" );
//             dispat.forward( request, response );
           
      
//     } catch (Exception ex) {
//         ex.printStackTrace();
//         Logger.getLogger(ListeProduitType.class.getName()).log(Level.SEVERE,null,ex);
//     }
//     }

//     // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//     /**
//      * Handles the HTTP <code>GET</code> method.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     /**
//      * Handles the HTTP <code>POST</code> method.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     /**
//      * Returns a short description of the servlet.
//      *
//      * @return a String containing servlet description
//      */
//     @Override
//     public String getServletInfo() {
//         return "Short description";
//     }// </editor-fold>

// }
