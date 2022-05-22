         <%@page import="model.TableRestau"%>
<%@page import="model.Commande"%>
<% 
Commande[] pl=(Commande[])request.getAttribute("listcommande"); 
TableRestau[] tabla=(TableRestau[])request.getAttribute("listtable"); 

%>
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Listes des Commandes</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Table</th>
                            <th>Date</th>
                        
                        </tr>
                      </thead>
                      <tbody>
                         <% for (int i = 0;i<pl.length;i++) { %>
                        <tr>
                            <td><% out.print(tabla[Integer.parseInt(pl[i].getTableRestau())].getNom()); %></td>
                            <td><a href="listecommande?id=<% out.print(pl[i].getId()); %>"><% out.print(pl[i].getDateCommande()); %></a></td>
                        </tr>
                        <% } %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>