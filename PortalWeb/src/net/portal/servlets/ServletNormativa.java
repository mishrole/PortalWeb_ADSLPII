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
			registrarNormativa(request, response);
		}
	}

	private void registrarNormativa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre;
		nombre = request.getParameter("nombre");
		
		Normativa bean = new Normativa();
		bean.setNormativa_nombre(nombre);
		
		int salida = servicioNormativa.guardarNormativa(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		}else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/normativa.jsp").forward(request, response);
		
	}

	private void listarTodasNormativas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Normativa> lista = servicioNormativa.listarNormativas();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

}
