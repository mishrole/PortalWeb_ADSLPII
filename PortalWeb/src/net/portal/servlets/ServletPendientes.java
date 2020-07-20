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
import net.portal.entidad.NuevaSolicitud;
import net.portal.service.PendientesService;

/**
 * Servlet implementation class ServletPendientes
 */
@WebServlet("/ServletPendientes")
public class ServletPendientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PendientesService servicioPendientes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPendientes() {
        super();
        servicioPendientes = new PendientesService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("LISTAR")) {
			listarSolicitudesPendientes(request, response);
		} else if (action.equals("BUSCAR")) {
			buscarSolicitudPendiente(request, response);
		} else if (action.equals("GESTIONAR")) {
			gestionarSolicitudPendiente(request, response);
		}
	}

	private void buscarSolicitudPendiente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		NuevaSolicitud bean = servicioPendientes.buscarPendiente(Integer.parseInt(codigo));
		request.setAttribute("pendiente", bean);
		request.getRequestDispatcher("/gestionaSolicitud.jsp").forward(request, response);;
	}

	private void gestionarSolicitudPendiente(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void listarSolicitudesPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ListarSolicitudes> lista = servicioPendientes.listarPendientes();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

}
