
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <%@include file="WEB-INF/bootstrap.jsp" %>
           <link rel="stylesheet" href="Css/style.css">
        <title>JSP Page</title>
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
      <li class="breadcrumb-item active">Mesas</li>
    </ol>

    <!-- Content Row -->
    <div class="row">
      <!-- Sidebar Column -->
      <%@include file="WEB-INF/Sidebar.jsp" %>
       <div class="col-lg-9 mb-4">
       <c:choose>
              <c:when test="${not empty Edit}">
                 
                        <h2>${accion}</h2>
                     
                        <form action="Mesas" method="post" >
                                <input type="hidden" name="idMesa" value="${Edit.id}" />
                                <input type="hidden" name="Editar" value= 1 />
                            <p>
                                <label>Nombre de Mesa: </label>
                                <input type="text" name="txtMesa" value="${Edit.nombre}" />
                            </p>
                            <p>
                                <label>Ubicacion: </label>
                                <input type="text" name="txtUbicacion" value="${Edit.ubicacion}" />
                            </p>
                            
                            <input type="submit" value="Confirmar Edicion"/>
                        </form>
                           
              </c:when>
                  <c:otherwise>
                        <h2>${accion}</h2>
                        <br>
                        <form action="Mesas" method="post" >
                            <input type="hidden" name="idMesa" value= 0 />
                            <input type="hidden" name="Editar" value= 0 />
                            <p>
                                <label>Nombre de Mesa: </label>
                                <input type="text" name="txtMesa"  />
                            </p>
                            <p>
                                <label>Ubicacion: </label>
                                <input type="text" name="txtUbicacion" />
                            </p>
                            
                            <input type="submit" value="Cargar Mesa"/>
                        </form>
                  </c:otherwise>
              </c:choose>
            <br>
            <table class="table table-striped">
            <thead class="thead-dark">
            <tr><th>Mesa</th><th>Ubicacion</th><th>QR</th><th></th></tr>
            </thead>  
                <c:forEach items="${ Mesas }" var="item">
                    <tr><td>${item.nombre }</td><td> ${ item.ubicacion }</td><td> <img src="QR/Mesa${item.nombre}.jpg" alt="QR-${item.nombre}" width="100" height="100"></td>
                        <td><button type="button" class="btn btn-warning"><a href="Admin?page=ME&modo=EM&id=${item.id}">Editar</a></button></td>           
                    </tr>
                </c:forEach>
            </table>
          </div>
        </div>
       </div>
        <%@include file="WEB-INF/Footer.jsp" %>
    </body>
</html>
