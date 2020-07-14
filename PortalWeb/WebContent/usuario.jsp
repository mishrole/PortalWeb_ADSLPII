<jsp:include page="menu.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Nuevo Usuario</title>
</head>
<body>

<c:if test="${requestScope.MENSAJE!=null}">
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
	  <strong>${requestScope.MENSAJE}</strong>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>

	<div class="container">
		<h2 class="text-center mt-5 mb-5">Nuevo Usuario</h2>
		<form method="post" action="ServletUsuario?accion=REGISTRAR" id="solicitud-form">
			<div class="form-group row">
				<label for="inputLogin" class="col-sm-2 col-form-label">Usuario</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputLogin" name="login">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">Contraseña</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputPassword" name="password">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputNombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputNombre" name="nombre">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputApellido" class="col-sm-2 col-form-label">Apellido</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputApellido" name="apellido">
				</div>
			</div>
			<div class="form-group row">
      			<label class="col-sm-2 col-form-label" for="rol">Rol</label>
      			<div class="col-sm-10">
        			<select class="form-control" name="rol">
        				<option value="">[Seleccione]</option>
        					<c:forEach items="${requestScope.roles}" var="row">
        					<option value="${row.rol_id}">${row.rol_nombre}</option>
        					</c:forEach>
      				</select>
      			</div>
     		</div>
			<div class="text-center mt-5">
				<button type="submit" class="btn btn-primary pl-5 pr-5">Registrar</button>
				<button type="button" class="btn btn-secondary pl-5 pr-5">Ver todas</button>
			</div>
		</form>
	</div>
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!-- jQuery Validation (Minificados) -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
	
	<script>
		$("#solicitud-form").validate({
			rules: {
				login: {
					required: true
				},
				password: {
					required: true
				},
				nombre: {
					required: true
				},
				apellido: {
					required: true
				},
				rol: {
					required: true
				}
			},
			messages: {
				login: {
					required: 'Ingrese un usuario'
				},
				password: {
					required: 'Ingrese una contraseña'
				},
				nombre: {
					required: 'Ingrese un nombre'
				},
				apellido: {
					required: 'Ingrese un apellido'
				},
				rol: {
					required: 'Ingrese un rol'
				}
			},
			
			errorElement: 'span',
			errorPlacement: function (error, element) {
				error.addClass('invalid-feedback');
				element.closest('.col-sm-10').append(error);
			},
			highlight: function (element, errorClass, validClass) {
				$(element).addClass('is-invalid');
			},
			unhighlight: function (element, errorClass, validaCLass) {
				$(element).removeClass('is-invalid');
			}
			
		});
		
		$(".btn-secondary").click(function () {
			window.location.href='ServletUsuario?accion=LISTAR';
		})
	</script>

</body>
</html>