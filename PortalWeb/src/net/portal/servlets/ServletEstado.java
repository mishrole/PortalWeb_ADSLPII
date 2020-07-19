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

import net.portal.entidad.Estado;
import net.portal.service.EstadoService;

/**
 * Servlet implementation class ServletEstado
 */
@WebServlet("/ServletEstado")
public class ServletEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EstadoService servicioEstado;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEstado() {
        super();
        servicioEstado = new EstadoService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operacion");
		
		if(operation.equals("LISTAR")) {
			listarTodosEstados(request, response);
		}else if(operation.equals("REGISTRAR")) {
			registrarEstado(request, response);
		}
	}

	private void registrarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre;
		
		nombre = request.getParameter("nombre");
		
		Estado bean = new Estado();
		bean.setEstado_nombre(nombre);
		
		int salida = servicioEstado.guardarEstado(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		}else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/estado.jsp").forward(request, response);
	}

	private void listarTodosEstados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estado> lista = servicioEstado.listarEstados();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

}
