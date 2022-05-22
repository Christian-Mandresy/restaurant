<%@page import="model.TypePaiement"%>
<% 
TypePaiement[] t=(TypePaiement[])request.getAttribute("type"); 
%>
    <div class="form-group">
        
            <form action="TraitListPaie" method="post">
                   
                <h1>Payement entre deux dates</h1>   
                        
                    <label for="Date 1">Date 1</label>
                    <input type="date" name="date1">
                     <label for="Date 2">Date 2</label>
                    <input type="date" name="date2">
                     <label for="Table">Type Payement</label>
                      <select class="form-control" id="exampleSelectGender" name="type">
                        <% for (int i = 0;i<t.length;i++) { %>
                                <option  value="<% out.print(t[i].getId()); %>"><% out.print(t[i].getNom()); %></option>
                        <% } %>
                        </select>
                      </div>
                        <input type="submit" value="Voir">
                 
            </form>


