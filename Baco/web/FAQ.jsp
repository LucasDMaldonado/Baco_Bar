

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="WEB-INF/bootstrap.jsp" %>
        <link href="Css/styleFAQ.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="grid-container">
            <div class="header">
                <%@include file="WEB-INF/navbar.jsp" %>
            </div>

            <div class="left" style="background-color:#aaa;">Column</div>
            <div class="middle" >
                <div class="content">
                     <br />
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        En este lugar encontraras las preguntas frequentes que puedas tener sobre <strong>Baco-Bar</strong> y como funciona. Si no encuentra la respuesta
                        que busca aqui, no dude en contactarnos. 
                    </div>

                    <br />

                    <div class="panel-group" id="accordion">
                        <div class="faqHeader"><h1>Preguntas Generales</h1></div>
                        <div class="panel panel-default  ">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">¿Que es Baco-Bar?</a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    Baco-Bar es una plataforma que te permite Administrar tu Loca y tener un Menu online,desde el cual tus clientes podran ordenar y pagar  
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default  ">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTen">¿Que puedo hacer en Baco-Bar?</a>
                                </h4>
                            </div>
                            <div id="collapseTen" class="panel-collapse collapse">
                                <div class="panel-body">
                                    En Baco-Bar, Puedes Administrar la cantidad de mesas que posees, sus pedidos y cobrar atra vez de Mercado Pago. Tambien podras, mantener control de tu stock.
                                    crear tragos, ver estadisticas de tus ventas y administrar los pedidos de tus clientes.

                                </div>
                            </div>
                        </div>


                        <div class="faqHeader  "><h1>Funcionalidades</h1></div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven">Como administro mi salon?</a>
                                </h4>
                            </div>
                            <div id="collapseEleven" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Puedes usar la seccion de <strong>Administracion de mesas</strong>.Donde podras crear o eliminar mesas, asignarles una ubicacion y nombre, Obtener el codigo QR 
                                    desde el cual tus clientes podran acceder al Menu Online. 

                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Como administro los pedidos?</a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">
                                    En la seccion Pedidos podras ver todos los pedidos,cual es el contenido del mismo, desde que mesas provienen, si desean pagar en efectivo o si pagaron Atravez de 
                                    Mercado Pago
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">Como Creo mi Menu Online</a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse">
                                <div class="panel-body">
                                    solo tienes que crear tus tragos y Bebidas. Solo tienes que seleccionar una categoria, asignarle un nombre, decidir su precio, 
                                    y agregar tantos ingredientes como desees y Baco-Bar se hara cargo del resto. 
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">Como manejo mi stock?</a>
                                </h4>
                            </div>
                            <div id="collapseFive" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Para ello podras acceder a la seccion Stock, donde podras cargar todos los productos que utilices, desde los ingredientes de tus Tragos 
                                    y los todos los micelaneos que utilices en tu local. Solo deberas asignarle un nombre, la cantidad si es o no un ingrediente y quien es tu proveedor.
                                    Ahora podras administrar tus productos muy sensillamente, agregando o quitando a medida que reabastescas o utilices tu stock
                                    <br />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">Como puedo ver mis ventas?</a>
                                </h4>
                            </div>
                            <div id="collapseSix" class="panel-collapse collapse">
                                <div class="panel-body">
                                    En la seccion Ventas, podras encontrar tu historial de ventas, estadisticas tales como:
                                    <ul>
                                        <li>Estadistica de ventas mensuales</li>
                                        <li>Trago mas vendido</li>
                                        <li>Trago Menos vendido</li>
                                        <li>Total de ventas</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading vertical-center">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">Como mis clientes Realizaran sus pedidos?</a>
                                </h4>
                            </div>
                            <div id="collapseEight" class="panel-collapse collapse vertical-center">
                                <div class="panel-body">
                                    Ellos accederan al Menu Online atravez un Codigo QR. El cual podras obtener desde la seccion de Administracion de Mesas. una vez alli ellos seleccionaran 
                                    los tragos que deseen y tu podras verlos desde la seccion de Pedidos.
                                    Ademas tus clientes podran decidir si desean pagar en efectivo o a traves de Mercado Pago.
                                </div>
                            </div>
                        </div>
                    </div></div>  
                <div class="right" style="background-color:#ccc;">Column</div>

                <div class="footer">
                    <%@include file="WEB-INF/Footer.jsp" %>
                </div>
            </div>
    </body>
</html>

