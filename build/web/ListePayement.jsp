<%@page import="model.VuePaiement"%>
<%
    VuePaiement[] pl = (VuePaiement[]) request.getAttribute("liste");
    Object t = request.getAttribute("total");
%>
<div class="row">
    <div class="col-md-12 stretch-card">
        <div class="card">
            <div class="card-body">
                <p class="card-title">Listes des payement</p>
                TOTAL = <%= t%>
                <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                        <thead>
                            <tr>
                                <th>Client</th>
                                <th>Table</th>
                                <th>Date de payement</th>
                                <th>Montant</th>
                                <th>Type</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < pl.length; i++) { %>
                            <tr>
                                <td><% out.print(pl[i].getNomClient()); %></td>
                                <td><% out.print(pl[i].getTable()); %></td>
                                <td><% out.print(pl[i].getDatePaiement()); %></td>
                                <td><% out.print(pl[i].getMontant()); %></td>
                                <td><% out.print(pl[i].getNomPaiement()); %></td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>