<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="WEB-INF/bootstrap.jsp" %>

        
        <c:choose>
            <c:when test="${not empty Edit}">                
                 <script>
                    var counter =1

                    function addNew() {
                        counter++;
                        document.getElementById("contador").value = counter
                        var mainContainer = document.getElementById('mainContainer');
                        var newDiv = document.createElement('div');
                        var newDropdown = document.createElement('select');
                        newDropdown.setAttribute("id", "Ingredientes" + counter)
                        newDropdown.setAttribute("name", "cmbIngredientes" + counter)
                    <c:forEach items='${ingredientes}' var='item'>
                        newDropdownOption = document.createElement("option");
                        newDropdownOption.value = "${ item.id}";
                        newDropdownOption.text = "${ item.nombre }";
                        newDropdown.add(newDropdownOption);
                    </c:forEach>
                        var newAddButton = document.createElement('input');
                        newAddButton.type = "button";
                        newAddButton.value = " + ";
                        var newDelButton = document.createElement('input');
                        newDelButton.type = "button";
                        newDelButton.value = " - ";
                        newDiv.appendChild(newDropdown);
                        newDiv.appendChild(newAddButton);
                        newDiv.appendChild(newDelButton);
                        mainContainer.appendChild(newDiv);
                        newAddButton.onclick = addNew;
                        newDelButton.onclick = function () {
                            this.parentNode.remove();
                        };
                    }


                </script>
            </c:when>
            <c:otherwise>
                <script>
                    var counter =1;

                    function addNew() {
                        counter++;
                        document.getElementById("contador").value = counter
                        var mainContainer = document.getElementById('mainContainer');
                        var newDiv = document.createElement('div');
                        var newDropdown = document.createElement('select');
                        newDropdown.setAttribute("id", "cmbIngredientes" + counter)
                        newDropdown.setAttribute("name", "cmbIngredientes" + counter)
                    <c:forEach items='${ingredientes}' var='item'>
                        newDropdownOption = document.createElement("option");
                        newDropdownOption.value = "${ item.id}";
                        newDropdownOption.text = "${ item.nombre }";
                        newDropdown.add(newDropdownOption);
                    </c:forEach>
                        var newAddButton = document.createElement('input');
                        newAddButton.type = "button";
                        newAddButton.value = " + ";
                        var newDelButton = document.createElement('input');
                        newDelButton.type = "button";
                        newDelButton.value = " - ";
                        newDiv.appendChild(newDropdown);
                        newDiv.appendChild(newAddButton);
                        newDiv.appendChild(newDelButton);
                        mainContainer.appendChild(newDiv);
                        newAddButton.onclick = addNew;
                        newDelButton.onclick = function () {
                            mainContainer.removeChild(newDiv);
                            counter--;
                            document.getElementById("contador").value = counter
                        };
                    }


                </script>
            </c:otherwise>
        </c:choose>

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
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item active">Tragos</li>
            </ol>

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <%@include file="WEB-INF/Sidebar.jsp" %>
                <!-- Content Column -->
                <div class="col-lg-9 mb-4">

                    <c:choose>
                        <c:when test="${not empty Edit}">
                            <h2>${accion}</h2>

                            <form action="Trago" method="post">
                                <input type="hidden" name="idTrago" value="${Edit.trag.id}" />
                                <input type="hidden" id= "contador" name="contador" value= ${contador} />                    
                                <input type="hidden" name="Editar" value= 1 />
                                <p>
                                    <label>Categoria: </label>
                                    <select name="cmbCategoria">
                                        <c:forEach items="${ Categoria }" var="item">
                                            <option value="${ item.id }" <c:if test="${item.id == Edit.trag.categoria}">selected</c:if>>${ item.categoria }</option>
                                        </c:forEach>                               
                                    </select>

                                </p>
                                <p>
                                    <label>Nombre del Trago: </label>
                                    <input type="text" name="txtNombre" value="${Edit.trag.nombre}" />
                                </p>
                                <p>
                                    <label>Precio: </label>
                                    <input type="text" name="txtPrecio" value ="${Edit.trag.precio}" />
                                </p>
                                <p>
                                    <label>Ingredientes: </label>
                                    <br>
                                <div id="mainContainer">
                                    <c:forEach items="${Edit.prod}" var= "Edit" varStatus="count">
                                        <c:choose>
                                            <c:when test="${count.count == 1}">

                                                <select id="Ingredientes${count.count}" name="cmbIngrediente${count.count}">
                                                    <c:forEach items="${ ingredientes }" var="item">
                                                        <option value="${ item.id}" <c:if test="${item.id == Edit.id}">selected</c:if>>${item.nombre}</option>
                                                    </c:forEach>                               
                                                </select>
                                                <input type="button" value=" + " onClick="addNew();"> 

                                            </c:when>
                                            <c:otherwise>
                                                <div>
                                                    <select id="Ingredientes${count.count}" name="cmbIngrediente${count.count}">
                                                        <c:forEach items="${ ingredientes }" var="item">
                                                            <option value="${ item.id}" <c:if test="${item.id == Edit.id}">selected</c:if>>${item.nombre}</option>
                                                        </c:forEach>                               
                                                    </select>
                                                    <input type="button" value=" + " onClick="addNew();"> 
                                                    <input type="button" value=" - " onclick="this.parentNode.remove();"> 
                                                </div> 
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>    
                                </p>


                                <input type="submit" value="Confirmar modificacion" />
                            </form>
                        </c:when>

                        <c:otherwise>
                            <h2>${accion}</h2>


                            <form action="Trago" method="post" >
                                <p>
                                    <input type="hidden" name="Editar" value= 0 />
                                    <input type="hidden" id= "contador" name="contador" value= 1 />
                                    <label>Categoria: </label>
                                    <select name="cmbCategoria">
                                        <c:forEach items="${ Categoria }" var="item">
                                            <option value="${ item.id }">${ item.categoria }</option>
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
                                <br>
                                <div id="mainContainer">
                                    <select id="Ingredientes1" name='cmbIngredientes1'>
                                        <c:forEach items='${ingredientes}' var='item'>
                                            <option value='${ item.id}'>${ item.nombre }</option>
                                        </c:forEach>
                                    </select>
                                    <input type="button" value=" + " onClick="addNew();">
                                </div>
                                <br>
                                <br>
                                <br>
                                <p>
                                    <input type="submit" value="cargar Trago"/> 
                                </p>

                            </form>
                        </c:otherwise>      
                    </c:choose>  

                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr><th>Trago</th><th>Categoria</th><th>Precio</th><th>Disponible</th><th></th></tr>
                        </thead>
                        <c:forEach items="${ lstTrago }" var="item">
                            <tr><td>${ item.trag.nombre }</td><td> ${ item.trag.categoria }</td><td> ${ item.trag.precio }</td>
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
                                <td><button type="button" class="btn btn-warning"><a href="Admin?page=TR&modo=ET&id=${item.trag.id}">Editar</a></button></td>           
                            </tr>
                        </c:forEach> 
                    </table>
                </div>     
            </div>
        </div>
        <%@include file="WEB-INF/Footer.jsp" %>  
    </body>
</html>
