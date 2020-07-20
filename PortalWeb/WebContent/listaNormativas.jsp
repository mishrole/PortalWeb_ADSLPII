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
<title>Lista de Normativas</title>
</head>
</head>
<body>
<jsp:include page="menu.jsp"/>

<div class="container">
		<h2 class="text-center mt-5 mb-5">Lista de Normativas</h2>
		<button type="submit" class="btn btn-primary">Nueva Normativa</button><p>
			
			<table id="table_id" class="table table-striped table-bordered">
			    <thead>
			        <tr>
			            <th>C�digo</th>
			            <th>Nombre</th>
			            <th>Acci�n</th>
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
	    	"info": false
	    });
	    
		$("#table_id tbody").empty();
		
		$.getJSON("ServletNormativa?operacion=LISTAR", {}, function(response) {
			$.each(response, function(index, item) {
				$("#table_id").append(
					"<tr><td>" + item.normativa_id +
					"</td><td>" + item.normativa_nombre +
					"</td><td> <a href=''>Eliminar</a> </td></tr>"
				)
			})
		});
	} );
	
	$(".btn-primary").click(function() {
		window.location.href='normativa.jsp';
	})
	</script>

</body>
</html>