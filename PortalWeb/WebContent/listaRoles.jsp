<jsp:include page="menu.jsp"/>
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
<title>Lista de Roles</title>
</head>
</head>
<body>

<div class="container">
		<h2 class="text-center mt-5 mb-5">Lista de Roles</h2>
		<button type="submit" class="btn btn-primary">Nuevo Rol</button><p>
			
			<table id="table_id" class="display">
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Nombre</th>
			            <!--<th></th> -->
			        </tr>
			    </thead>
			    <tbody>
					<c:forEach items="${requestScope.roles}" var="item">
						<tr>
				         	<td>${item.rol_id}</td>
				         	<td>${item.rol_nombre}</td>
				         	<!-- <td><a href="ServletNormativa?accion=buscar&codigo=${row.normativa_id}">Editar</a></td> -->
				        </tr>
					</c:forEach>
			    </tbody>
			</table>
		</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<script type="text/javascript">
	$(document).ready( function () {
	    $('#table_id').DataTable();
	} );
	
	$(".btn-primary").click(function() {
		window.location.href='rol.jsp';
	})
	</script>

</body>
</html>