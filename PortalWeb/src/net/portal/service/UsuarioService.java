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
	
	public List<Usuario> listarUsuarios() {
		return daoUsuario.listUsuarios();
	}
	
	public int registrarUsuario(Usuario bean) {
		return daoUsuario.insertUsuario(bean);
	}
	
	public int actualizarUsuario(Usuario bean) {
		return daoUsuario.updateUsuario(bean);
	}
	
	public int eliminarUsuario(int codigo) {
		return daoUsuario.deleteUsuario(codigo);
	}
	
	public List<Usuario> buscarTecnicoXApellido(String apellido) {
		return daoUsuario.listAllTecnicosXApellido(apellido);
	}
} 
