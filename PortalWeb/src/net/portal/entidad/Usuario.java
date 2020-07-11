package net.portal.entidad;

public class Usuario {
	
	private int usuario_id;
	private String usuario_login;
	private String usuario_password;
	private String usuario_nombre;
	private String usuario_apellido;
	private int rol_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getUsuario_login() {
		return usuario_login;
	}
	public void setUsuario_login(String usuario_login) {
		this.usuario_login = usuario_login;
	}
	public String getUsuario_password() {
		return usuario_password;
	}
	public void setUsuario_password(String usuario_password) {
		this.usuario_password = usuario_password;
	}
	public String getUsuario_nombre() {
		return usuario_nombre;
	}
	public void setUsuario_nombre(String usuario_nombre) {
		this.usuario_nombre = usuario_nombre;
	}
	public String getUsuario_apellido() {
		return usuario_apellido;
	}
	public void setUsuario_apellido(String usuario_apellido) {
		this.usuario_apellido = usuario_apellido;
	}
	public int getRol_id() {
		return rol_id;
	}
	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}
}
