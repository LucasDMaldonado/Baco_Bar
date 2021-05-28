<%-- 
    Document   : Principal
    Created on : 24 may. 2021, 19:20:34
    Author     : panic
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <%@include file="WEB-INF/Header.jsp" %>

<body>
  
    <div class='Container'>
        <nav  id="menu" class="nav">	
	<ul>
		<li>
			<a  href="#" title="Salon">
				<span  class="icon"> <i aria-hidden="true"  class="icon-spoon-knife"></i></span><span>Salon</span>
			</a>
		</li>
		<li>		
			<a href="#" title="Tragos"><span class="icon"> <i aria-hidden="true" class="icon-glass2"></i></span><span>Tragos</span></a>	
		</li>	
		<li>
			<a  href="#" title="Producto"><span  class="icon"><i  aria-hidden="true" class="icon-truck"></i></span><span>Productos</span></a>
		</li>
		<li>
			<a  href="#" title="Ventas"><span  class="icon"><i  aria-hidden="true" class="icon-stats-dots"></i></span><span>Ventas</span></a>	
		</li>

	</ul>
</nav>
    </div>
  
     
  <br>
  <br>
  <br>
  
  <%@include file="WEB-INF/Footer.jsp" %>
    
     </body>
</html>
