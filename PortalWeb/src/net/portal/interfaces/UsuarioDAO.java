package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Usuario;
import net.portal.entidad.Menu;

public interface UsuarioDAO {
	/*	Inicio de sesión */
	public Usuario iniciarSesion(String login, String password);
	public List<Menu> getMenusUsuario(int id);
	/*	Mantenimiento Usuario */
	public List<Usuario> listUsuarios();
	public int insertUsuario(Usuario bean);
	public int deleteUsuario(int codigo);
	public int updateUsuario(Usuario bean);
	/*	Filtros	*/
	public List<Usuario> listAllTecnicosXApellido(String apellido);
}
