<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Gestionar Solicitud</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<c:if test="${requestScope.MENSAJE!=null}">
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
	  <strong>${requestScope.MENSAJE}</strong>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>

<div class="container">
<h2 class="text-center mt-5 mb-5">Gestionar Solicitud</h2>
<form method="post" action="ServletPendientes?accion=GESTIONAR" id="solicitud-form" enctype="multipart/form-data">

  <div class="form-group row">
    <label for="inputCodigo" class="col-sm-2 col-form-label">Código</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputCodigo" name="codigo" disabled>  
    </div>
    <label for="inputFecha" class="col-sm-2 col-form-label">Fecha</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputFecha" name="fecha" disabled>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNombre" class="col-sm-2 col-form-label">Nombre</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputNombre" name="nombre" disabled>  
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputResumen" class="col-sm-2 col-form-label">Resumen</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="inputResumen" rows="3" name="resumen" disabled></textarea>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="inputNormativa" class="col-sm-2 col-form-label">Normativa</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputNormativa" name="normativa" disabled>  
    </div>
    
    <label class="col-sm-2 col-form-label" for="estado">Estado</label>
      <div class="col-sm-4">
        <select class="form-control" name="estado">
	        <option value="">[Seleccione]</option>
	        <option value="2">Aprobado</option>
	        <option value="3">Rechazado</option>
      	</select>
      </div>
  </div>
  
  <div class="form-group row">
    <label for="inputDocumento" class="col-sm-2 col-form-label"></label>
    <div class="col-sm-10 text-center">
      <button type="button" class="btn btn-info btn-block">Visualizar PDF</button> 
    </div>
  </div>

   <div class="form-group row">
    <label for="inputTecnico" class="col-sm-2 col-form-label">Técnico</label>
    <div class="col-sm-8">
      <input type="text" class="form-control" id="inputTecnico" name="tecnico" disabled>  
    </div>
    <div class="col-sm-2">
    	<button type="button" id="btnTecnico" class="btn btn-secondary pl-5 pr-5">Asignar</button>
    </div>
  </div>

    <div class="text-center mt-5">
      <button type="submit" class="btn btn-primary pl-5 pr-5">Guardar</button>
      <button type="button" class="btn btn-secondary pl-5 pr-5">Cancelar</button>
    </div>
    
</form>
</div>

 <div class="modal fade" id="modalTecnico">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4>Buscar Técnico</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<form id="idRegistra" accept-charset="UTF-8"  class="form-inline" method="post">						
	            	<div class="panel-group col-sm-12" id="steps">
						<div class="panel panel-default">
							<div class="panel-heading">
							</div>
							<div class="panel-body">
	                          	<div class="form-group pb-4">
	                               <label class="pr-2 control-label" for="id_reg_nombre">Apellido</label>
								   <input class="form-control col-sm-6" id="idBuscarApellido"/>
								   <button type="button" class="btn btn-primary ml-5 pl-4 pr-4" id="btnBuscarTecnico">Buscar</button>
	                           </div>
	                           <div id="divAlumno">
	                           		<table id="id_tableTecnico" class="table table-striped table-bordered text-center" >
										<thead>
												<tr>
													<th>Codigo</th>
													<th>Nombre</th>
													<th>Apellido</th>
													<th>Acción</th>
												</tr>
										</thead>
										<tbody>
			
										</tbody>
									</table>											
							   </div>
	                       </div>		                        
	                  	</div>
	                  </div>
	            </form>      
			</div>
		</div>
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

	<script>
	
	$("#btnTecnico").click(function () {
		$("#modalTecnico").modal("show");
	});
	
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
	
	$(document).on("click", "#btnVerTecnico", function() {
		var codigo, nombre, apellido;
		codigo = $(this).parents("tr").find("td")[0].innerHTML;
		nombre = $(this).parents("tr").find("td")[1].innerHTML;
		apellido = $(this).parents("tr").find("td")[2].innerHTML;
		$("#inputTecnico").val("["+codigo+"] "+nombre+" "+apellido);
	});
	/*
		$("#solicitud-form").validate({
			rules: {
				nombre: {
					required: true
				},
				resumen: {
					required: true
				},
				normativa: {
					required: true
				},
				archivo: {
					required: true
				}
			},
			messages: {
				nombre: {
					required: 'Ingrese un nombre'
				},
				resumen: {
					required: 'Ingrese un resumen'
				},
				normativa: {
					required: 'Seleccione una normativa'
				},
				archivo: {
					required: 'Seleccione un archivo'
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
	*/
	</script>
</body>
</html>