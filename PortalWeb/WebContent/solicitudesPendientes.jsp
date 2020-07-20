<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<title>Solicitudes Pendientes</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<c:if test="${sessionScope.usuario.nombre == null}">
	<script>
		console.log("No está logueado");
		window.location.href = "index.jsp";
	</script>
</c:if>

<c:if test="${requestScope.MENSAJE!=null}">
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
	  <strong>${requestScope.MENSAJE}</strong>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>

	<div class="container">
		<h2 class="text-center mt-5 mb-5">Solicitudes Pendientes</h2>
			
			<table id="table_id" class="table table-striped table-bordered">
			    <thead>
			        <tr>
			            <th>Código</th>
			            <th>Fecha</th>
			            <th>Estado</th>
			            <th>Acción</th>
			        </tr>
			    </thead>
			    <tbody>
			    </tbody>
			</table>
		</div>

	<!-- Optional JavaScript -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/v/bs4/jq-3.3.1/dt-1.10.21/datatables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<script>

	$(document).ready( function () {
	    $('#table_id').DataTable({
	    	searching: false,
	    	"info": false,
	    	lengthChange: false
	    });
	    
	    
		$("#table_id tbody").empty();
		
		$.getJSON("ServletPendientes?accion=LISTAR", {}, function(response) {
			$.each(response, function(index, item) {
				$("#table_id").append(
					"<tr><td>" + item.id +
					"</td><td>" + item.fecha +
					"</td><td>" + item.estado +
					"</td><td>" +
					"<a href='ServletPendientes?accion=BUSCAR&codigo="+ item.id +
					"'>Gestionar</a></td></tr>"
				)
			})
		});
	} );
	
	</script>
</body>
</html>