<%@page import="net.portal.entidad.Usuario"%>
<%

	Usuario bean=(Usuario)  session.getAttribute("usuario");
	//System.out.print("Prueba : "+bean);
	if(bean==null)
	response.sendRedirect("login.jsp");
%>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <div class="navbar-header">
	      <a class="navbar-brand" href="#">Portal Web</a>
	  </div>
	  <ul class="navbar-nav mr-auto">
		<c:forEach items="${sessionScope.menus}" var="row">	
		    <li class="nav-item">
		      <a class="nav-link" href="${row.url}">${row.descripcion}</a>
		    </li>
		</c:forEach>
	    
	  </ul>
	  <ul class="navbar-nav">
	  	<li><a class="nav-link" href="ServletUsuario?accion=CERRAR">Cerrar Sesión</a></li>
	  </ul>
	  
	</nav>

