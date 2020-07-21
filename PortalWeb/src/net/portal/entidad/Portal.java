package net.portal.entidad;

public class Portal {
	
	private int portal_id;
	private String portal_fecha;
	
	private int solicitud_id;
	private int usuario_id;
	
	private int visibilidad_id;
	
	public int getPortal_id() {
		return portal_id;
	}
	public void setPortal_id(int portal_id) {
		this.portal_id = portal_id;
	}
	public String getPortal_fecha() {
		return portal_fecha;
	}
	public void setPortal_fecha(String portal_fecha) {
		this.portal_fecha = portal_fecha;
	}
	public int getSolicitud_id() {
		return solicitud_id;
	}
	public void setSolicitud_id(int solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getVisibilidad_id() {
		return visibilidad_id;
	}
	public void setVisibilidad_id(int visibilidad_id) {
		this.visibilidad_id = visibilidad_id;
	}
}
