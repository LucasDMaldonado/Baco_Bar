<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <%@include file="WEB-INF/bootstrap.jsp" %>
           <link rel="stylesheet" href="Css/style.css">
           <script type="text/javascript"> 
                document.getElementById('Casos').onchange = function() {
                    localStorage.setItem('selecteditem', document.getElementById('Casos').value);
                };

                if (localStorage.getItem('selectedtem')) {
                    document.getElementById('caso_'+localStorage.getItem('selecteditem')).selected = true;
                } 
            </script>
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
                     
                        <form action="Producto" method="post" >
                                <input type="hidden" name="idStock" value="${Edit.id}" />
                                <input type="hidden" name="idProd" value="${Edit.id_producto}" />
                                <input type="hidden" name="idUsado" value="${Edit.usado}" />
                                <input type="hidden" name="Editar" value= 1 />
                            <p>
                                <label>Nombre de Producto: </label>
                                <input type="text" name="txtProd" value="${Edit.nomProd}" />
                            </p>
                            <p>
                                <label>Nombre de Proveedor: </label>
                                <input type="text" name="txtProv" value="${Edit.proveedor}" />
                            </p>
                            <p>
                                <label>Cantidad: </label>
                                <input type="text" name="txtCant" value="${Edit.cantidad}" />
                            </p>
                            <c:choose>
                                <c:when test="${Edit.ingrediente==true}">
                                    <p>
                                        <input type="checkbox" name="chkingrediente" value= 1 checked> Ingrediente 
                                    </p>                                   
                                </c:when>
                                <c:otherwise>
                                    <p>
                                       <input type="checkbox" name="chkingrediente" value= 1 > Ingrediente 
                                    </p>                                   
                                </c:otherwise>
                            </c:choose>                   
                            <input type="submit" value="Confirmar Edicion"/>
                        </form>
                           
              </c:when>
                  <c:otherwise>
                        <h2>${accion}</h2>
                        <br>
                        <form action="Producto" method="post" >
                            <input type="hidden" name="idStock" value= 0 />
                            <input type="hidden" name="idProd" value= 0 />              
                            <input type="hidden" name="Editar" value= 0 />
                            <p>
                                <label>Nombre de Producto: </label>
                                <input type="text" name="txtProd"/>
                            </p>
                            <p>
                                <label>Nombre de Proveedor: </label>
                                <input type="text" name="txtProv"/>
                            </p>
                            <p>
                                <label>Cantidad: </label>
                                <input type="text" name="txtCant"/>
                            </p>
                            <p>
                                <input type="checkbox" name="chkingrediente" value= 1 > Ingrediente<BR>
                            </p>
                            
                            <input type="submit" name="Cargar" value="Cargar Producto"/>
                        </form>
                  </c:otherwise>
              </c:choose>
            <br>
            <select name="Casos" id="Casos" onchange="self.location=self.location+'&tbl='+this.selectedIndex">
                        <option id="caso_1" value="1">Todo</option>
                        <option id="caso_2" value="2">Ingrediente</option>
                        <option id="caso_3" value="3">Micelaneo</option>
            </select>
           
           
            <br>
            <br>           
            <table class="table table-striped">
            <thead class="thead-dark">
            <tr><th>Producto</th><th>Proveedor</th><th>Stock</th><th>Ingrediente</th><th>Ultima actualizacion</th><th></th></tr>
            </thead>  
                <c:forEach items="${Stock}" var="item">
                    <tr><td>${item.nomProd}</td>
                        <td>${item.proveedor}</td>
                        <td>${item.cantidad}</td>                        
                         <c:choose>
                                <c:when test="${item.ingrediente==true}">
                                   <td> SI </td>                                   
                                </c:when>
                                <c:otherwise>
                                   <td> NO </td>                              
                                </c:otherwise>
                            </c:choose>
                        <td>${item.fecha}</td>
                        <td>
                            <form action="Producto" method="post" >
                                <input type="hidden" name="idstock" value= ${item.id} />
                                 <input type="hidden" name="cant" value= ${item.cantidad} />
                                <input type="hidden" name="Editar" value= 1 />
                                <input type="text" size="10" name="txtStock"/>
                                <input type="submit" name="agregar" value="Agregar"/>
                                <input type="submit" name="quitar" value="Quitar"/>
                            </form>
                           
                        </td>           
                    </tr>
                </c:forEach>
            </table>
          </div>
        </div>
      </div>
        <%@include file="WEB-INF/Footer.jsp" %>
    </body>
</html>