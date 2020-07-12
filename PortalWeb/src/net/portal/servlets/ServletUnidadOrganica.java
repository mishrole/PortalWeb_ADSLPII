package net.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.portal.entidad.NuevaSolicitud;
import net.portal.service.SolicitudService;
import net.portal.dao.MySqlSolicitudDAO;
import net.portal.entidad.ListarSolicitudes;

/**
 * Servlet implementation class ServletMantenimientoSolicitud
 */
@WebServlet("/ServletUnidadOrganica")
@MultipartConfig
public class ServletUnidadOrganica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SolicitudService servicioSolicitud;

    public ServletUnidadOrganica() {
    	super();
        servicioSolicitud = new SolicitudService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("REGISTRAR")) {
			registrarSolicitud(request, response);
		}else if(action.equals("LISTAR")) {
			listarSolicitudesPresentadas(request, response);
		}
	}

	private void listarSolicitudesPresentadas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ListarSolicitudes> lista = servicioSolicitud.listarPresentadas();
		request.setAttribute("presentadas", lista);
		request.getRequestDispatcher("/solicitudesPresentadas.jsp").forward(request, response);
	}

	private void registrarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre, resumen, normativa, nombreArchivo;
		
		nombre = request.getParameter("nombre");
		resumen = request.getParameter("resumen");
		normativa = request.getParameter("normativa");
		
		Part archivo = request.getPart("archivo");
		nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
		InputStream contenido = null;

		contenido = archivo.getInputStream();
		
		/*	Objeto de Clase Solicitud */
		NuevaSolicitud bean = new NuevaSolicitud();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		bean.setFecha(sdf.format(date));
		bean.setUsuario(1); // Seteado temporal (todavía no está listo)
		bean.setEstado("1"); // Estado por defecto : En proceso
		
		bean.setSolicitud_nombre(nombre);
		bean.setSolicitud_resumen(resumen);
		bean.setNormativa_id(normativa);
		bean.setSolicitud_file(contenido);
		
		int salida = servicioSolicitud.registraSolicitud(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		} else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		//response.sendRedirect("registraSolicitud.html");
		request.getRequestDispatcher("/registraSolicitud.jsp").forward(request, response);
	}

}
