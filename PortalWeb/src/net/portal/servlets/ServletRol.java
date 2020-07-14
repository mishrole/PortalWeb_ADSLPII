package net.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.portal.entidad.Rol;
import net.portal.service.RolService;

/**
 * Servlet implementation class ServletRol
 */
@WebServlet("/ServletRol")
public class ServletRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RolService servicioRol;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRol() {
        super();
        servicioRol = new RolService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operacion");
		
		if(operation.equals("LISTAR")) {
			listarTodosRoles(request, response);
		}else if(operation.equals("REGISTRAR")) {
			registrarRol(request, response);
		}
	}

	private void registrarRol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre;
		nombre = request.getParameter("nombre");
		
		Rol bean = new Rol();
		bean.setRol_nombre(nombre);
		
		int salida = servicioRol.guardarRol(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		}else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/rol.jsp").forward(request, response);
		
	}

	private void listarTodosRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rol> lista = servicioRol.listarRoles();
		request.setAttribute("roles", lista);
		request.getRequestDispatcher("/listaRoles.jsp").forward(request, response);
		
	}

}
