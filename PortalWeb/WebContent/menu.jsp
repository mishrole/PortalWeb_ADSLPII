<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="net.portal.entidad.Usuario"%>
<%
	Usuario bean=(Usuario)  session.getAttribute("usuario");
	if(bean == null) {
		response.sendRedirect("login.jsp");
	}else {
		response.sendRedirect("home.jsp");
	}
%>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <div class="navbar-header">
	      <a class="navbar-brand" href="home.jsp">Portal Web</a>
	  </div>
	  <ul class="navbar-nav mr-auto">
		<c:forEach items="${sessionScope.menus}" var="row">	
		    <li class="nav-item">
		      <a class="nav-link" href="${row.url}">${row.descripcion}</a>
		    </li>
		</c:forEach>
	  </ul>
	  <ul class="navbar-nav">
	  	<span class="nav-item nav-link"> ${sessionScope.usuario.nombre} ${sessionScope.usuario.apellido}</span>
	  	<c:choose>
		    <c:when test="${sessionScope.usuario.nombre != null}">
		        <li><a class="nav-link" href="ServletUsuario?accion=CERRAR">Cerrar Sesión</a></li>
		    </c:when>
		    <c:otherwise>
		        <li><a class="nav-link" href="login.jsp">Iniciar Sesión</a></li>
		    </c:otherwise>
		</c:choose>
	  </ul>
	</nav>

	
