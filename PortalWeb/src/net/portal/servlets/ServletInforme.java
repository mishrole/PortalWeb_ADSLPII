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

import net.portal.entidad.Informe;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.entidad.Usuario;
import net.portal.service.InformeService;

/**
 * Servlet implementation class ServletInforme
 */
@WebServlet("/ServletInforme")
public class ServletInforme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InformeService servicioInforme;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInforme() {
        super();
        servicioInforme = new InformeService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("LISTAR")) {
			listarInformesPendientes(request, response);
		} else if (action.equals("BUSCAR")) {
			buscarSolicitudInforme(request, response);
		} else if (action.equals("GESTIONAR")) {
			gestionarSolicitudInforme(request, response);
		}
	}

	private void gestionarSolicitudInforme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo, argumento;
		codigo = request.getParameter("codigo");
		argumento = request.getParameter("argumento");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("usuario");
		
		Informe informe = new Informe();
		informe.setInforme_fecha(sdf.format(date));
		informe.setSolicitud_id(Integer.parseInt(codigo));
		informe.setUsuario_id(user.getId());
		informe.setInforme_argumento(argumento);
		
		ListarSolicitudes solicitud = new ListarSolicitudes();
		solicitud.setId(Integer.parseInt(codigo));
		
		int salidaInforme = servicioInforme.guardarInforme(informe);
		int salidaSolicitud = servicioInforme.actualizarEstadoSolicitud(solicitud);
		
		if(salidaInforme != -1 && salidaSolicitud != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		}else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/informesPendientes.jsp").forward(request, response);
		
	}

	private void buscarSolicitudInforme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		NuevaSolicitud bean = servicioInforme.buscarInforme(Integer.parseInt(codigo));
		request.setAttribute("informe", bean);
		request.getRequestDispatcher("/gestionaInforme.jsp").forward(request, response);
	}

	private void listarInformesPendientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ListarSolicitudes> lista = servicioInforme.listarInformesPendientes();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

}
