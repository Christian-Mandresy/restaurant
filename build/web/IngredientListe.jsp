         <%@page import="model.EnregistrementIngredient"%>
<% 
EnregistrementIngredient[] pl=(EnregistrementIngredient[])request.getAttribute("liste"); 
Object t=request.getAttribute("total");
%>
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Listes des Ingredients</p>
                  TOTAL = <%= t  %>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Ingredient</th>
                            <th>Quantite</th>
                            <th>Prix</th>
                        </tr>
                      </thead>
                      <tbody>
                         <% for (int i = 0;i<pl.length;i++) { %>
                        <tr>
                            <td><% out.print(pl[i].getNom()); %></td>
                            <td><% out.print(pl[i].getQuantite()); %></td>
                            <td><% out.print(pl[i].getPrix()); %></td>
                        </tr>
                        <% } %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>