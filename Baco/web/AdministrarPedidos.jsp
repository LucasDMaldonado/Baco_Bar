<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <title>JSP Page</title>
             <%@include file="WEB-INF/bootstrap.jsp" %>
             <link rel="stylesheet" href="Css/style.css">
    </head>
    
    <body>
  <%@include file="WEB-INF/navbar.jsp" %>

  <br>
  <br>
  <br>
  
  <div class="container">

      <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">User
      <small>Administracion</small>
    </h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="Admin">Home</a>
      </li>
      <li class="breadcrumb-item active">Pedidos</li>
    </ol>

    <!-- Content Row -->
    <div class="row">
      <!-- Sidebar Column -->
    <%@include file="WEB-INF/Sidebar.jsp" %>
    <div class="col-lg-9 mb-4">
      <h2>${accion}</h2>

            <c:forEach items="${Mesas}" var="item">
                <input type="hidden" id="fecha" value="${item.pedido.ped.fechaPedido}">     
                <div class="row">
                     <div class="col-md-7">
                         <img class="img-fluid rounded mb-3 mb-md-0" src="ImgsBaco/icono_pedido.png" width="360">       
                     </div>
                     <div class="col-md-5">
                     <h3>${item.mesa.nombre}</h3>
                     <p>Tiempo del pedido</p>
                     <p id="timer"></p>
                     <a class="ver" href="#more">Ver Pedido</a>
                     <div class="extender">
                        <table class="table table-striped">
                           <thead class="thead-dark">
                             <tr><th>Trago</th><th>cantidad</th><th>Precio</th></tr>
                           </thead>
                           <c:forEach items="${item.pedido.detalle }" var="item2">
                             <tr><td>${item2.pedido.detalle.nombre}</td><td> ${ item2.pedido.detalle.cant}</td><td> ${ item2.pedido.detalle.precio }</td>        
                             </tr>
                           </c:forEach> 
                        </table>
                        <a href="#more:after">Cerrar</a>
                     </div>        
                    </div>
                </div>
                <hr>
             </c:forEach>
        </div>         
    </div>
 </div> 
  <script>
      function func()
      {
          var fechaValue = decument.getElementById("fecha").value;
          var fecha = Math.abs((new Date(fechaValue).getTime()/1000).toFixed(0));
          var fechaActual = Math.abs((new Date().getTime()/1000).toFixed(0));          
          var diff = fechaActual - fecha;
          
          var horas = Math.floor(diff/3600)%24;
          var minutos = Math.floor(diff/60)%60;
          var segundos = diff/60
          
          document.getElementById("timer").innerHTML = horas +":"+ minutos +":"+ segundos;          
      }
      func();
  </Script>
  
  <%@include file="WEB-INF/Footer.jsp" %>
    
     </body>
</html>