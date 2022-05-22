<%@page import="model.TableRestau"%>
<%@page import="model.Serveur"%>
<%@page import="model.Produit"%>
<%
    Produit[] listprod=(Produit[])request.getAttribute("produit");
    Serveur[] listserveur=(Serveur[])request.getAttribute("serveur");
    TableRestau[] listtable=(TableRestau[])request.getAttribute("table");
%>
    <div class="form-group">
        
            <form action="TraitCommande" method="post">
                    <label for="Serveur">Serveur</label>
                      <select class="form-control" id="exampleSelectGender" name="serveur">
                          <% for (int i = 0;i<listserveur.length;i++) { %>
                                                <option  value="<% out.print(listserveur[i].getId()); %>"><% out.print(listserveur[i].getNom()); %>
                                                </option>
                                    <% } %>
                        </select>
                      </div>
                        
                    <label for="Table">Table</label>
                      <select class="form-control" id="exampleSelectGender" name="table">
                          <% for (int i = 0;i<listtable.length;i++) { %>
                                                <option  value="<% out.print(listtable[i].getId()); %>"><% out.print(listtable[i].getNom()); %>
                                                </option>
                                    <% } %>
                        </select>
                      </div>
        
                        <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Nom</th>
                            <th>prix</th>
                            <th>Qte</th>
                  
                        </tr>
                      </thead>
                      <tbody>
   
                         <% for (int i = 0;i<listprod.length;i++) { %>
                        <tr>
                            <td><% out.print(listprod[i].getNom()); %></td>
                            <td> <input type="text" name="prix<% out.print(i); %>" value="<% out.print(listprod[i].getPrix()); %>" readonly> </td>
                            <td> <input type="number" name="qte<% out.print(i); %>" value="0"> </td>
                            
                        </tr>
                      
                        <% } %>
                       
                      </tbody>
                    </table>
                        <input type="submit" value="commander">
                  <form/>

                  </div>
           

                  </form>


