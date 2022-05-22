<%@page import="model.TableRestau"%>
<%@page import="model.Serveur"%>
<%@page import="model.Produit"%>
<%
    TableRestau[] listtable=(TableRestau[])request.getAttribute("table");
%>
    <div class="form-group">
        
            <form action="TraitChoixTable" method="post">
                   
                     
                        
                    <label for="Table">Table</label>
                      <select class="form-control" id="exampleSelectGender" name="table">
                          <% for (int i = 0;i<listtable.length;i++) { %>
                                                <option  value="<% out.print(listtable[i].getId()); %>"><% out.print(listtable[i].getNom()); %>
                                                </option>
                                    <% } %>
                        </select>
                      </div>
                        <input type="submit" value="Choisir">
                 
            </form>


