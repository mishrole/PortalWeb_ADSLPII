package net.portal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.portal.entidad.ListarSolicitudes;
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

	private void gestionarPublicacionPendiente(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void buscarPublicacionPendiente(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
