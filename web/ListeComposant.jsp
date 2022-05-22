         <%@page import="model.ComposantIngredient"%>
<% 
ComposantIngredient[] pl=(ComposantIngredient[])request.getAttribute("comp"); 

%>
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Composant Produit</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Produit</th>
                            <th>Ingredient</th>
                            <th>Quantite</th>
                            <th>Prix</th>
                           
                        </tr>
                      </thead>
                      <tbody>
                         <% for (int i = 0;i<pl.length;i++) { %>
                        <tr>
                            <td><% out.print(pl[i].getProduit()); %></td>
                            <td><% out.print(pl[i].getIngredient()); %></td>
                            <td><% out.print(pl[i].getQte()); %></td>
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