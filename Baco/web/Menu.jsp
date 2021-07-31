<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="WEB-INF/bootstrap.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <script src="https://www.w3schools.com/lib/w3.js">window.onload = myMain;
            function myMain() {
                document.getElementById("menu").onclick = buton;
            }
            function buton(e) {
                if (e.target.tagName == 'BUTTON') {
                    var counter = 1
                    function addNew() {
                        document.getElementById("contador").value = counter
                        var mainContainer = document.getElementById('Order');
                        var id = e.target.id;
                        var newDiv = document.createElement('div');
                        newdiv.class = "w3-container w3-white w3-padding-32";
                        var newOrder = document.createElement('input');
                        newOrder.type = "hidden";
                        newOrder.setAttribute("name", "Trago" + id)
                        newOrder.value = document.getElementById(id).innerHTML;
                        var newOrder2 = document.createElement('input');
                        newOrder2.type = "hidden";
                        newOrder2.setAttribute("name", "cantidad" + id)
                        newOrder2.value = counter;
                        var newH1 = document.createElement('h1')
                        var newB = document.createElement('b')
                        var newSpan = document.createElement('span')
                        newOrder2.value = counter
            <c:forEach items="${Tragos}" var = "item">
                <c:if test="${item.trag.id == id}">
                        newB.innerHTML = ${item.trag.nombre}
                        newSpan.innerHTML = counter + "X $" + ${item.trag.precio};
                        var newAddButton = document.createElement('input');
                        newAddButton.type = "button";
                        newAddButton.value = " Agregar ";
                        var newDelButton = document.createElement('input');
                        newDelButton.type = "button";
                        newDelButton.value = "Quitar";
                        newH1.appendChild(newB);
                        newH1.appendChild(newSpan);
                        newDiv.appendChild(newH1);
                        mainContainer.appendChild(newDiv);
                        newAddButton.onclick = addNew;
                        newDelButton.onclick = function () {
                            if (counter > 0) {
                                counter--;
                                document.getElementById("cantidad" + id).value = counter
                            } else
                                mainContainer.removeChild(newDiv);
                        };
                </c:if>
            </c:forEach>

                    }
                }
            }</script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Amatic+SC">
        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Amatic SC", sans-serif}
        </style>       
    </head>

    <body>

        <%@include file="WEB-INF/navbar.jsp" %>
        <br>
        <br>
        <br>

        <div id="menu" class="w3-container w3-black w3-xxlarge w3-padding-64">
            <h1 class="w3-center w3-jumbo w3-padding-32">THE MENU</h1>
            <div class="w3-row w3-center w3-border w3-border-dark-grey">
                <a href="#BCA"><div class="w3-third w3-padding-large w3-red">Bebidas con alcohol</div></a>
                <a href="#BSA"><div class="w3-third w3-padding-large w3-hover-red">Bebidas sin alcohol</div></a>
                <a href="#TCA"><div class="w3-third w3-padding-large w3-hover-red">Tragos con alcohol</div></a>
                <a href="#TSA"><div class="w3-third w3-padding-large w3-hover-red">Tragos sin alcohol</div></a>
            </div>
            <div id="BCA" class="w3-container w3-white w3-padding-32">
                <c:forEach items="${Tragos}" var="item">
                    <c:if test="${item.trag.categoria == 2}">

                        <h1 ><b id="nombre${item.trag.id}">${item.trag.nombre}</b> <span id="precio"class="w3-right w3-tag w3-dark-grey w3-round">$ ${item.trag.precio}</span></h1>

                        <p>
                            <button id="${item.trag.id}"type="button" class="btn btn-primary btn-lg " onclick="addNew();">Agregar</button>
                        </p><p class="w3-text-grey">

                        </p>
                        <hr>
                    </c:if>
                </c:forEach>
            </div>
            <h1 id="BSA" class="w3-center w3-jumbo w3-padding-32">Bebidas sin alcohol</h1>
            <div class="w3-container w3-white w3-padding-32">        
                <c:forEach items="${Tragos}" var="item">
                    <c:if test="${item.trag.categoria == 3}">

                        <h1 id="${item.trag.id}"><b>${item.trag.nombre}</b> <span id="${item.trag.id}Precio" class="w3-right w3-tag w3-dark-grey w3-round">$ ${item.trag.precio}</span></h1>
                        <p>
                            <button id="${item.trag.id}"type="button" class="btn btn-primary btn-lg " onclick="addNew();">Agregar</button>
                        </p>
                        <p class="w3-text-grey">

                        </p>
                        <hr>
                    </c:if>
                </c:forEach>
            </div>
            <h1 id="TCA" class="w3-center w3-jumbo w3-padding-32">Tragos con alcohol</h1>
            <div class="w3-container w3-white w3-padding-32">
                <c:forEach items="${Tragos}" var="item">
                    <c:if test="${item.trag.categoria == 1}">

                        <h1><b>${item.trag.nombre}</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$ ${item.trag.precio}</span></h1>
                        <p>
                            <button id="${item.trag.id}"type="button" class="btn btn-primary btn-lg " onclick="addNew();">Agregar</button>
                        </p>
                        <p class="w3-text-grey">
                            <c:forEach items="${item.prod}" var="ing">
                                ${ing.nombre},
                            </c:forEach>
                        </p>
                        <hr>
                    </c:if>
                </c:forEach>
            </div>

            <h1 id="TSA" class="w3-center w3-jumbo w3-padding-32">Tragos sin alcohol</h1>
            <div class="w3-container w3-white w3-padding-32">


                <c:forEach items="${Tragos}" var="item">
                    <c:if test="${item.trag.categoria == 4}">

                        <h1><b>${item.trag.nombre}</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$ ${item.trag.precio}</span></h1>
                        <p>
                            <button id="${item.trag.id}"type="button" class="btn btn-primary btn-lg " onclick="addNew();">Agregar</button>
                        </p>
                        <p class="w3-text-grey">
                            <c:forEach items="${item.prod}" var="ing">
                                ${ing.nombre},
                            </c:forEach>
                        </p>
                        <hr>
                    </c:if>
                </c:forEach>
            </div>
            <h1 id="TSA" class="w3-center w3-jumbo w3-padding-32">Tu Pedido</h1>
            <div id ="Order" class="w3-container w3-white w3-padding-32">

            </div>
        </div>



        <%@include file="WEB-INF/Footer.jsp" %>

    </body>
</html>
