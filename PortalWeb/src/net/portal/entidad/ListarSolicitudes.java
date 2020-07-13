package net.portal.entidad;

public class ListarSolicitudes {
	private int id;
	private String fecha;
	private int usuario;
	private String estado;
	private int tecnico;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getTecnico() {
		return tecnico;
	}
	public void setTecnico(int tecnico) {
		this.tecnico = tecnico;
	}
	
	
}
