package net.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import net.portal.entidad.NuevaSolicitud;
import net.portal.entidad.Usuario;
import net.portal.service.NormativaService;
import net.portal.service.SolicitudService;
import net.portal.service.UsuarioService;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.Menu;
import net.portal.entidad.Normativa;

/**
 * Servlet implementation class ServletMantenimientoSolicitud
 */
@WebServlet("/ServletSolicitud")
@MultipartConfig
public class ServletSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SolicitudService servicioSolicitud;
	private NormativaService servicioNormativa;

    public ServletSolicitud() {
    	super();
        servicioSolicitud = new SolicitudService();
        servicioNormativa = new NormativaService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("REGISTRAR")) {
			registrarSolicitud(request, response);
		}else if(action.equals("LISTAR")) {
			listarSolicitudesPresentadas(request, response);
		}else if(action.equals("LISTAR_NORMATIVAS")) {
			listarTodasNormativas(request, response);
		}
	}

	private void listarTodasNormativas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Normativa> lista = servicioNormativa.listarNormativas();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void listarSolicitudesPresentadas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("usuario");
		int codigo = user.getId();
		
		List<ListarSolicitudes> lista = servicioSolicitud.listarPresentadas(codigo);
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void registrarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre, resumen, normativa;
		
		nombre = request.getParameter("nombre");
		resumen = request.getParameter("resumen");
		normativa = request.getParameter("normativa");
		
		Part archivo = request.getPart("archivo");
		//String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
		InputStream contenido = null;

		contenido = archivo.getInputStream();
		
		NuevaSolicitud bean = new NuevaSolicitud();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		HttpSession session=request.getSession();
		Usuario user = (Usuario) session.getAttribute("usuario");
		
		bean.setFecha(sdf.format(date));
		bean.setUsuario(user.getId());
		bean.setEstado("1"); // Estado por defecto : En espera
		
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
		
		request.getRequestDispatcher("/registraSolicitud.jsp").forward(request, response);
	}

}
