        
<%@page import="model.Serveur"%>
<%
    Serveur[] listserveur=(Serveur[])request.getAttribute("serveur");
%>
    <div class="form-group">
        <form action="Pourboir" method="post">
                      <label for="Serveur">Serveur</label>
                      <select class="form-control" id="exampleSelectGender" name="serveur">
                          <% for (int i = 0;i<listserveur.length;i++) { %>
                                                <option  value="<% out.print(listserveur[i].getId()); %>"><% out.print(listserveur[i].getNom()); %>
                                                </option>
                                    <% } %>
                        </select>
                      </div>
           
                    </div>
                    <div class="form-group">
                      <label for="Date">Date</label>
                      <input type="date" class="form-control" id="exampleInputCity1" placeholder="Location" name="date1">
                    </div>
                    
                    <div class="form-group">
                      <label for="Date">Date</label>
                      <input type="date" class="form-control" id="exampleInputCity1" placeholder="Location" name="date2">
                    </div>
                  
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                  </form>


