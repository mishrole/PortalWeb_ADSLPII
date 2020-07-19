package net.portal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.portal.entidad.Menu;
import net.portal.entidad.Rol;
import net.portal.entidad.Usuario;
import net.portal.service.RolService;
import net.portal.service.UsuarioService;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioService servicioUsuario;
	private RolService servicioRol;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        servicioUsuario = new UsuarioService();
        servicioRol = new RolService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if(action.equals("INICIAR")) {
			iniciarSesionUsuario(request, response);
		}else if (action.equals("CERRAR")) {
			cerrarSesionUsuario(request, response);
		}else if (action.equals("LISTAR")) {
			listarUsuarios(request, response);
		}else if (action.equals("REGISTRAR")) {
			registrarUsuario(request, response);
		}else if (action.equals("LISTAR_ROLES")) {
			listarTodosRoles(request, response);
		}
	}

	private void listarTodosRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rol> lista = servicioRol.listarRoles();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login, password, nombre, apellido, rol;
		
		login = request.getParameter("login");
		password = request.getParameter("password");
		nombre = request.getParameter("nombre");
		apellido = request.getParameter("apellido");
		rol = request.getParameter("rol");
		
		Usuario bean = new Usuario();
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setNombre(nombre);
		bean.setApellido(apellido);
		bean.setRol(Integer.parseInt(rol));
		
		int salida = servicioUsuario.registrarUsuario(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registró correctamente");
		}else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/usuario.jsp").forward(request, response);
		
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> lista = servicioUsuario.listarUsuarios();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void cerrarSesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}

	private void iniciarSesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Usuario bean = servicioUsuario.iniciarSesion(login, password);
		
		if(bean != null) {
			int loginUsuario = bean.getId();
			
			List<Menu> lista = servicioUsuario.getMenusUsuario(loginUsuario);
			HttpSession session = request.getSession();
			
			session.setAttribute("menus", lista);
			session.setAttribute("usuario", bean);
			
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else {
			request.setAttribute("MENSAJE", "usuario y/o contraseña incorrecta");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
