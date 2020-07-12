package net.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.portal.entidad.Menu;
import net.portal.entidad.Usuario;
import net.portal.service.UsuarioService;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        usuarioService = new UsuarioService();
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
		}
	}

	private void cerrarSesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}

	private void iniciarSesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Usuario bean = usuarioService.iniciarSesion(login, password);
		
		if(bean != null) {
			int loginUsuario = bean.getId();
			
			List<Menu> lista = usuarioService.getMenusUsuario(loginUsuario);
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
