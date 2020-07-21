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

import net.portal.entidad.Portal;
import net.portal.service.PortalService;

/**
 * Servlet implementation class ServletPortal
 */
@WebServlet("/ServletPortal")
public class ServletPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PortalService servicioPortal;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPortal() {
        super();
        servicioPortal = new PortalService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("operacion");
		
		if(action.equals("LISTAR")) {
			listarAllPortal(request, response);
		}
	}

	private void listarAllPortal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Portal> lista = servicioPortal.listarPortal();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
		
	}

}
