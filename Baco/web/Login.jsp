<%-- 
    Document   : Administrar
    Created on : 6 nov. 2020, 19:28:26
    Author     : panic
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Baco Bar</title>
        <%@include file="WEB-INF/bootstrap.jsp" %>
    </head>
    <body>
         <div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">Baco
      <small>Login</small>
    </h1>
 
    <!-- Content Row -->
    <div class="row">

      <!-- Content Column -->
      <div class="col-lg-9 mb-4">
                     <h1>Iniciar Sesión</h1>
        <% if(request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").equals("")) { %>
            <h2>${ mensajeError }</h2>
        <% } %>
        <form method="post" action="LoginBa">
            <p>
                <label>Usuario: </label>
                <input name="txtUsuario" />
            </p>
            <p>
                <label>Contraseña: </label>
                <input type="password" name="txtPass" />
            </p>
            <input type="submit" value="Iniciar Sesión" >
        </form>
      </div>
     
    </div>
    <!-- /.row -->

  </div>
    </body>
</html>

