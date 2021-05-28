<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <title>JSP Page</title>
             <%@include file="WEB-INF/bootstrap.jsp" %>
    </head>
    
    <body>
  
  <%@include file="WEB-INF/navbar.jsp" %>
  <br>
  <br>
  <br>
  
<div class="container">

  
    <c:forEach items="${Comercio}" var="come">
    <div class="row">
      <div class="col-md-7">
        <a href="Oferta?idR=${idRubro}&rubro=${Rubro}&idC=${come.idComercio}&come=${come.nombre}">
          <img class="img-fluid rounded mb-3 mb-md-0" src="ImagenesSubidas/${come.img.nombreImagen}.jpg" width="720" height="450">
        </a>
      </div>
      <div class="col-md-5">
        <h3>${come.nombre}</h3>
        <p>Chequea todas las nuevas Ofertas de ${come.nombre}</p>
        <a class="btn btn-primary" href="Oferta?idR=${idRubro}&rubro=${Rubro}&idC=${come.idComercio}&come=${come.nombre}">Ver Ofertas
          <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
      </div>
    </div>
     <hr>
   </c:forEach>  


  </div>
  
  <%@include file="WEB-INF/Footer.jsp" %>
    
     </body>
</html>