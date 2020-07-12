package net.portal.service;

import java.util.List;

import net.portal.entidad.Menu;
import net.portal.entidad.Usuario;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.UsuarioDAO;

public class UsuarioService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	UsuarioDAO daoUsuario = fabrica.getUsuarioDAO();
	
	public Usuario iniciarSesion(String login, String password) {
		return daoUsuario.iniciarSesion(login, password);
	}
	
	public List<Menu> getMenusUsuario(int id) {
		return daoUsuario.getMenusUsuario(id);
	}
}
