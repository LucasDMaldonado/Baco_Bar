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
         <div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">User
      <small>Administracion</small>
    </h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="index.html">Home</a>
      </li>
      <li class="breadcrumb-item active">About</li>
    </ol>

    <!-- Content Row -->
    <div class="row">
      <!-- Sidebar Column -->
      <div class="col-lg-3 mb-4">
        <div class="list-group">
          <a href="Home" class="list-group-item">Home</a>
          <a href="Administrar?page=CO" class="list-group-item">Administrar Comercio</a>
          <a href="Administrar?page=RU" class="list-group-item">Administrar Rubro</a>
          <a href="Administrar?page=OF" class="list-group-item">Administrar Oferta</a>
          <a href="Administrar?page=LC" class="list-group-item">Cantidad de comentarios por comercios</a>
          <a href="Administrar?page=MC" class="list-group-item">Moderacion de comentarios</a>
          <a href="Administrar?page=PV" class="list-group-item">Promedio de Valoraciones</a>
          <a href="Administrar?page=VXE" class="list-group-item">valoraciones por estrella</a>

        </div>
      </div>
      <!-- Content Column -->
      <div class="col-lg-9 mb-4">
       
          <c:choose>
              <c:when test="${not empty Edit}">
                  <h2>${accion}</h2>
                 
                    <form action="Upload" method="post">
                     <input type="hidden" name="idTrago" value="${Edit.trag.id}" />
                     <input type="hidden" name="Editar" value= 1 />
                    <p>
                        <label>Categoria: </label>
                        <select name="cmbCategoria">
                            <c:forEach items="${ Categorias }" var="item">
                                <option value="${ item.idCategoria }" <c:if test="${item.idCategoria == Edit.trag.categoria}">selected</c:if>>${ item.Categoria }</option>
                            </c:forEach>
                               
                        </select>
                    </p>
                    <p>
                        <label>Nombre del Trago: </label>
                        <input type="text" name="txtNombre" value="${Edit.trag.nombre}" />
                    </p>
                    <p>
                        <label>Ingredientes: </label>
                        <c:forEach items="${lstTrago.prod}" var= "Edit">
                        <select name="cmbIngrediente">
                            <c:forEach items="${ Ingredientes }" var="item">
                                <option value="${ item.idProducto}" <c:if test="${item.id == Edit.prod.id}">selected</c:if>>${ item.Categoria }</option>
                            </c:forEach>
                               
                        </select>
                        </c:forEach>
                    </p>

                    
                    <input type="submit" value="Confirmar modificacion" />
                </form>
              </c:when>
              
                    <c:otherwise>
                        <h2>${accion}</h2>
                      
                       
                        <form action="Upload" method="post" >
                                    <p>
                                        <input type="hidden" name="Editar" value= 0 />
                                        <label>Categoria: </label>
                                        <select name="cmbCategoria">
                                            <c:forEach items="${ Categorias }" var="item">
                                            <option value="${ item.idCategoria }">${ item.Categoria }</option>
                                            </c:forEach>

                                        </select>
                                    </p>
                                    <p>
                                        <label>Nombre del Trago: </label>
                                        <input type="text" name="txtTrago" />
                                    </p>
                                    <p>
                                        <label>Precio: </label>
                                        <input type="text" name="txtPrecio" />
                                    </p>

                                    <label>Ingredientes: </label>
                                    <input type="submit" value="Agregar ingrediente" id ="Add_cmb"/>
                                    <span id="cont"></span>  
                                    <input type="submit" value="cargar Trago"/>
                                </form>
                                <script>
                                    ddBtn = document.getElementById("add_dd");
                                    ddBtn.addEventListener("click",function(){
                                    c = document.getElementById("cont");
                                    dd = "  <select name='cmbIngredientes'>
                                            <c:forEach items='${ingredientes}' var='item'>
                                            <option value='${ item.id}'>${ item.nombre }</option>
                                            </c:forEach>

                                        </select>";
                                    c.innerHTML += dd;
                                    }
                                </script>

                    </c:otherwise>      
          </c:choose>  
        
          <table class="table table-striped">
            <thead class="thead-dark">
            <tr><th>Trago</th><th>Categoria</th><th>Precio</th><th>Disponible</th><th></th></tr>
            </thead>
            <c:forEach items="${ lstTrago }" var="item">
            <tr><td>${ item.trag.nombre }</td><td> ${ item.Categoria }</td><td> ${ item.trag.precio }</td>
                <td>
                    <c:choose>
                        <c:when test ="${item.trag.disponible}">
                            Si
                        </c:when>                       
                        <c:otherwise>
                            No
                        </c:otherwise>                      
                    </c:choose> 
                </td>
                <td><button type="button" class="btn btn-warning"><a href="Administrar?page=TR&modo=ETC&id=${item.idTrago}">Editar</a></button></td>           
            </tr>
            </c:forEach> 
       </table>
      </div>
     
    </div>
  </div>
        
    </body>
</html>
