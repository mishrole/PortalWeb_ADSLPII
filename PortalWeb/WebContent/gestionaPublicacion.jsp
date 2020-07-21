<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Gestionar Publicación</title>
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
<h2 class="text-center mt-5 mb-5">Gestionar Publicación</h2>
<form method="post" action="ServletPublicaciones?accion=GESTIONAR" id="gestionar-form">

  <div class="form-group row">
    <label for="inputCodigo" class="col-sm-2 col-form-label">Código</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputCodigo" name="codigo" value="${requestScope.publicacion.id}" readonly>  
    </div>
    <label for="inputFecha" class="col-sm-2 col-form-label">Fecha</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputFecha" name="fecha" value="${requestScope.publicacion.fecha}" readonly>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNombre" class="col-sm-2 col-form-label">Nombre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputNombre" name="nombre" value="${requestScope.publicacion.solicitud_nombre}" readonly>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputResumen" class="col-sm-2 col-form-label">Resumen</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="inputResumen" rows="3" name="resumen" readonly>${requestScope.publicacion.solicitud_resumen}</textarea>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNormativa" class="col-sm-2 col-form-label">Normativa</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputNormativa" name="normativa" value="${requestScope.publicacion.normativa_id}" readonly>  
    </div>
   
     <label class="col-sm-2 col-form-label" for="visibilidad">Visibilidad</label>
      <div class="col-sm-4 validarGestion">
        <select class="form-control" name="visibilidad" id="selectVisibilidad">
	        <option value="">[Seleccione]</option>
	        <option value="1">Visible</option>
	        <option value="2">Oculto</option>
      	</select>
      </div>
  </div>
  

    <div class="text-center mt-5">
      <button type="submit" class="btn btn-primary pl-5 pr-5">Guardar</button>
      <button type="button" class="btn btn-secondary pl-5 pr-5" id="cancelar">Cancelar</button>
    </div>
    
</form>
</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!-- jQuery Validation (Minificados) -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
	<script>
	
	$("#cancelar").click(function() {
		window.location.href = 'publicacionesPendientes.jsp';
	})
	

	$("#gestionar-form").validate({
		rules: {
			visibilidad: {
				required: true
			}
		},
		messages: {
			visibilidad: {
				required: 'Seleccione una opción'
			}
		},
		
		errorElement: 'span',
		errorPlacement: function (error, element) {
			error.addClass('invalid-feedback');
			element.closest('.validarGestion').append(error);
		},
		highlight: function (element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight: function (element, errorClass, validaCLass) {
			$(element).removeClass('is-invalid');
		}
		
	});
	</script>
</body>
</html>