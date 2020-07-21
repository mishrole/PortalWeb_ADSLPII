<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Ver Solicitud</title>
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
<h2 class="text-center mt-5 mb-5">Ver Solicitud</h2>

  <div class="form-group row">
    <label for="inputCodigo" class="col-sm-2 col-form-label">Código</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputCodigo" name="codigo" value="${requestScope.solicitud.id}" readonly>  
    </div>
    <label for="inputFecha" class="col-sm-2 col-form-label">Fecha</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputFecha" name="fecha" value="${requestScope.solicitud.fecha}" readonly>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNombre" class="col-sm-2 col-form-label">Nombre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputNombre" name="nombre" value="${requestScope.solicitud.solicitud_nombre}" readonly>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputResumen" class="col-sm-2 col-form-label">Resumen</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="inputResumen" rows="3" name="resumen" readonly>${requestScope.solicitud.solicitud_resumen}</textarea>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNormativa" class="col-sm-2 col-form-label">Normativa</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputNormativa" name="normativa" value="${requestScope.solicitud.normativa_id}" readonly>  
    </div>
   	   <label for="inputEstado" class="col-sm-2 col-form-label">Estado</label>
   	<div class="col-sm-4">
      <input type="text" class="form-control" id="inputEstado" name="estado" value="${requestScope.solicitud.estado}" readonly>  
    </div>
  </div>
  
  <div class="modal fade" id="modalInforme">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h3>Reporte de Informe</h3>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div id="informeData"></div>
			</div>
		</div>
	</div>
</div>

    <div class="text-center mt-5">
    	<button type="button" class="btn btn-primary pl-5 pr-5" id="verDetalleInforme" disabled>Ver Informe</button>
    	<button type="button" class="btn btn-secondary pl-5 pr-5" id="cancelar">Cerrar</button>
    </div>
    
</div>

		<!-- Optional JavaScript -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/v/bs4/jq-3.3.1/dt-1.10.21/datatables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!-- jQuery Validation (Minificados) -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>

<style>
	.btn-primary.disabled, .btn-primary:disabled {
		background-color: #666666 !important;
		border-color: #666666 !important;
	}
</style>

	<script>
	
	$("#cancelar").click(function() {
		window.location.href = 'solicitudesPresentadas.jsp';
	})
	
	$(document).ready(function() {
		
		var codigo = $("#inputCodigo").val();
		
		$.getJSON("ServletSolicitud?accion=VER_INFORME", {codigo: codigo}, function(data) {
			/*$("#informeData").append(
			"<p>"+ data.informe_id +"</p>"		
			)*/
			//console.log(data.length);
			//console.log(Object.keys(data).length);
			
			var argumento = data.informe_argumento;
			
			if(argumento == "") {
				argumento = "Solicitud publicada exitosamente";
			}
		
			$("#informeData").append(
				"<p>Informe con código " + data.informe_id + 
				"</br>Generado el " + data.informe_fecha.substring(8, 10) + "/" + data.informe_fecha.substring(5, 7) + "/" + data.informe_fecha.substring(0, 4) +
				" a las " + data.informe_fecha.substring(11, 19) +
				"</p><p>Tras revisar su solicitud número " + data.solicitud_id +
				", presentada con fecha " + $("#inputFecha").val().substring(8, 10) + "/" + $("#inputFecha").val().substring(5, 7) + "/" + $("#inputFecha").val().substring(0, 4) +
				", se determinó que este se encuentra: <b>" + $("#inputEstado").val() +"</b></p></br>" +
				"<h5>Comentarios adicionales</h5></br>" + argumento
			);
			
			if(data == null) {
				$("#verDetalleInforme").prop("disabled", true);
			}else {
				$("#verDetalleInforme").prop("disabled", false);
			}
		});
	});

		
	$("#verDetalleInforme").click(function () {
		$("#modalInforme").modal("show");
	});
	
	/*
	$("#btnBuscarTecnico").click(function () {
		var apellido = $("#idBuscarApellido").val();
		
		$("#id_tableTecnico tbody").empty();
		
		$.getJSON("ServletUsuario?accion=CONSULTAR_TECNICO", {apellido:apellido}, function(data) {
			$.each(data, function (index, item) {
				$("#id_tableTecnico").append(
						"<tr><td>" + item.id +
						"</td><td>" + item.nombre + 
						"</td><td>" + item.apellido + 
						"</td><td>" +
						"<button type='button' class='btn btn-primary' id='btnVerTecnico'>Ver</button></td></tr>"
				);
			});
		});
	});
	*/
	</script>
</body>
</html>