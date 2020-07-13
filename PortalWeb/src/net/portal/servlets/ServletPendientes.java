package net.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.portal.entidad.ListarSolicitudes;
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
		}
	}

	private void listarSolicitudesPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ListarSolicitudes> lista = servicioPendientes.listarPendientes();
		request.setAttribute("pendientes", lista);
		request.getRequestDispatcher("/solicitudesPendientes.jsp").forward(request, response);
		
	}

}
