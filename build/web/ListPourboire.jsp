<%@page import="model.Pourboire"%>
<%
 Object[] temp=(Object[])request.getAttribute("pourboire");
 Pourboire[] liste=new Pourboire[temp.length];
 for(int i=0;i<temp.length;i++){
   liste[i]=(Pourboire)temp[i];
 }
 Object o=request.getAttribute("prix");
 float prix=(float)o;
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Admin</title>
  <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="assets/vendors/base/vendor.bundle.base.css">
  <link rel="stylesheet" href="assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="assets/css/style.css">
  <link rel="shortcut icon" href="assets/images/favicon.png" />
</head>
<body>

  <div class="container-scroller">
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="navbar-brand-wrapper d-flex justify-content-center">
        <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">  
          <h3>Administrateur</h3>
        </div>  
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <ul class="navbar-nav mr-lg-4 w-100">
          <li class="nav-item nav-search d-none d-lg-block w-100">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text" id="search">
                  <i class="mdi mdi-magnify"></i>
                </span>
              </div>
              <input type="text" class="form-control" placeholder="Intitulé" aria-label="search" aria-describedby="search">
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" style="font-size:25px" href="#" data-toggle="dropdown" id="profileDropdown">
              <i style="color:rgb(77,131,255)" class="mdi mdi-account-circle"></i>
              <span class="nav-profile-name" style="font-size:15px">Admin</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item" href="<%=request.getContextPath()+"/index.jsp"%>" onclick="deco()">
                <i class="mdi mdi-logout text-primary"></i>
                  Se déconnecter
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="mdi mdi-menu"></span>
        </button>
      </div>
    </nav>
    <div class="container-fluid page-body-wrapper">


      <div class="main-panel">


        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">Pourboire</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Serveur</th>
                            <th>Prix</th>
                            <th>Date</th>
                        </tr>
                      </thead>
                      <tbody>
                      <%for(int i=0;i<liste.length;i++){ %>
                        <tr>
                            <td><%=liste[i].getServeur()%></td>
                            <td><% out.println(liste[i].getPrix()); %></td>
                            <td><% out.println(liste[i].getDatecommande()); %></td>
                        </tr>
                      <% } %>
                      </tbody>
                    </table>
                    TOTAL = <%=prix%>
                  </div>
                </div>
              </div>
            </div>
          </div>
      
      </div>

      <footer class="footer">
        <div class="d-sm-flex justify-content-center justify-content-sm-between">
        </div>
      </footer>
    </div>
  </div>
</div>

<script src="assets/vendors/base/vendor.bundle.base.js"></script>
<script src="assets/vendors/chart.js/Chart.min.js"></script>
<script src="assets/vendors/datatables.net/jquery.dataTables.js"></script>
<script src="assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<script src="assets/js/off-canvas.js"></script>
<script src="assets/js/hoverable-collapse.js"></script>
<script src="assets/js/template.js"></script>
<script src="assets/js/dashboard.js"></script>
<script src="assets/js/data-table.js"></script>
<script src="assets/js/jquery.dataTables.js"></script>
<script src="assets/js/dataTables.bootstrap4.js"></script>
<script src="assets/js/jquery.cookie.js" type="text/javascript"></script>
</body>
</html>