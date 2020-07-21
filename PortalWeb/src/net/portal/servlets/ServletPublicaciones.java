package net.portal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.entidad.Portal;
import net.portal.entidad.Usuario;
import net.portal.service.PublicacionesService;

/**
 * Servlet implementation class ServletPublicaciones
 */
@WebServlet("/ServletPublicaciones")
public class ServletPublicaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicacionesService servicioPublicaciones;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPublicaciones() {
        super();
        servicioPublicaciones = new PublicacionesService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("LISTAR")) {
			listarPublicacionesPendientes(request, response);
		} else if (action.equals("BUSCAR")) {
			buscarPublicacionPendiente(request, response);
		} else if (action.equals("GESTIONAR")) {
			gestionarPublicacionPendiente(request, response);
		}
	}

	private void gestionarPublicacionPendiente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo, visibilidad;
		codigo = request.getParameter("codigo");
		visibilidad = request.getParameter("visibilidad");
		
		Portal portal = new Portal();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		HttpSession session=request.getSession();
		Usuario user = (Usuario) session.getAttribute("usuario");
		
		portal.setPortal_fecha(sdf.format(date));
		portal.setSolicitud_id(Integer.parseInt(codigo));
		portal.setUsuario_id(user.getId());
		portal.setVisibilidad_id(Integer.parseInt(visibilidad));
		
		ListarSolicitudes publicacion = new ListarSolicitudes();
		publicacion.setId(Integer.parseInt(codigo));
	
		int salidaPortal = servicioPublicaciones.guardarPublicacion(portal);
		int salidaPublicacion = servicioPublicaciones.actualizarPublicacion(publicacion);
		
		if(salidaPortal != -1 && salidaPublicacion != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		} else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/publicacionesPendientes.jsp").forward(request, response);
	}

	private void buscarPublicacionPendiente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		NuevaSolicitud bean = servicioPublicaciones.buscarPublicacion(Integer.parseInt(codigo));
		request.setAttribute("publicacion", bean);
		request.getRequestDispatcher("/gestionaPublicacion.jsp").forward(request, response);
		
	}

	private void listarPublicacionesPendientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ListarSolicitudes> lista = servicioPublicaciones.listarPublicaciones();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

}
