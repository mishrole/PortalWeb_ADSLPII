package net.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.portal.entidad.Normativa;
import net.portal.service.NormativaService;

/**
 * Servlet implementation class ServletNormativa
 */
@WebServlet("/ServletNormativa")
public class ServletNormativa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private NormativaService servicioNormativa;
    
    public ServletNormativa() {
        super();
        servicioNormativa = new NormativaService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operacion");
		
		if(operation.equals("LISTAR")) {
			listarTodasNormativas(request, response);
		}else if(operation.equals("REGISTRAR")) {
			
		}
	}

	private void listarTodasNormativas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Normativa> lista = servicioNormativa.listarNormativas();
		request.setAttribute("normativas", lista);
		request.getRequestDispatcher("/listaNormativas.jsp").forward(request, response);
	}

}
