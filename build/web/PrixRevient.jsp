         <%@page import="model.Produit"%>
<% 
Produit[] pl=(Produit[])request.getAttribute("plat"); 

%>
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Listes des plats</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Plat</th>
                            <th>Prix</th>
                            <th>Prix de revient</th>
                           
                        </tr>
                      </thead>
                      <tbody>
                         <% for (int i = 0;i<pl.length;i++) { %>
                        <tr>
                            <td><% out.print(pl[i].getNom()); %></td>
                            <td><% out.print(pl[i].getPrix()); %></td>
                            <td><a href="FicheComposant?id=<% out.print(pl[i].getId()); %>"><% out.print(pl[i].PrixRevient()); %></td>
                        </tr>
                        <% } %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>