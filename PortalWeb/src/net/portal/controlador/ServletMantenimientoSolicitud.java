package net.portal.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.portal.entidad.Solicitud;
import net.portal.model.modelSolicitud;

/**
 * Servlet implementation class ServletMantenimientoSolicitud
 */
@WebServlet("/ServletMantenimientoSolicitud")
@MultipartConfig
public class ServletMantenimientoSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private modelSolicitud mSolicitud;

    public ServletMantenimientoSolicitud() {
    	super();
        mSolicitud = new modelSolicitud();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("REGISTRAR")) {
			registrarSolicitud(request, response);
		}
	}

	private void registrarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre, resumen, normativa, nombrArchivo;
		
		nombre = request.getParameter("nombre");
		resumen = request.getParameter("resumen");
		normativa = request.getParameter("normativa");
		
		Part archivo = request.getPart("archivo");
		nombrArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
		InputStream contenido = null;

		contenido = archivo.getInputStream();

		
		/*	Objeto de Clase Solicitud */
		Solicitud bean = new Solicitud();
		
		int estado = 1; // Estado por defecto : En proceso
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		bean.setSolicitud_fecha(sdf.format(date));
		bean.setUsuario_id(1); // Seteado temporal (todavía no está listo)
		bean.setEstado_id(estado);
		
		bean.setSolicitud_nombre(nombre);
		bean.setSolicitud_resumen(resumen);
		bean.setNormativa_id(Integer.parseInt(normativa));
		bean.setSolicitud_file(contenido);
		
		mSolicitud.registrarSolicitud(bean);
		
		response.sendRedirect("registraSolicitud.html");
	}

}
