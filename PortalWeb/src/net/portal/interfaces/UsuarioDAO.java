package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Usuario;
import net.portal.entidad.Menu;

public interface UsuarioDAO {
	public Usuario iniciarSesion(String login, String password);
	public List<Menu> getMenusUsuario(int id);
}
