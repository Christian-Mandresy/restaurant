<%@page import="model.Produit"%>
<%@page import="model.TypeProduit"%><% 
Produit[] pl=(Produit[])request.getAttribute("plat"); 
TypeProduit[] type=(TypeProduit[])request.getAttribute("type"); 

%>
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Listes des plats</p>
                  <div class="table-responsive">
                  <form action="ProduitListeType" method="Get">
                         <select class="form-control" name="cat" style="width: 15%;">
                                     <% for (int i = 0;i<type.length;i++) { %>
                                                <option  value="<% out.print(type[i].getId()); %>"><% out.print(type[i].getNom()); %>
                                                </option>
                                    <% } %>
                                </select>
                           <p> <input class="btn btn-success" type="submit" name="valider" value="Valider" style="margin-top: 10px;"></p>
                       </form>
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Plat</th>
                            <th>Prix</th>
                           
                        </tr>
                      </thead>
                      <tbody>
                         <% for (int i = 0;i<pl.length;i++) { %>
                        <tr>
                            <td><% out.print(pl[i].getNom(); %></td>
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