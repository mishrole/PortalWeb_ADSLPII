package net.portal.entidad;

import java.io.InputStream;
import java.util.Date;

public class Solicitud {
	private int solicitud_id;
	private String solicitud_fecha;
	private int usuario_id;
	private int estado_id;
	
	private String solicitud_nombre;
	private String solicitud_resumen;
	private int normativa_id;
	private InputStream solicitud_file;
	
	public int getSolicitud_id() {
		return solicitud_id;
	}
	public void setSolicitud_id(int solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	public String getSolicitud_fecha() {
		return solicitud_fecha;
	}
	public void setSolicitud_fecha(String solicitud_fecha) {
		this.solicitud_fecha = solicitud_fecha;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getEstado_id() {
		return estado_id;
	}
	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}
	public String getSolicitud_nombre() {
		return solicitud_nombre;
	}
	public void setSolicitud_nombre(String solicitud_nombre) {
		this.solicitud_nombre = solicitud_nombre;
	}
	public String getSolicitud_resumen() {
		return solicitud_resumen;
	}
	public void setSolicitud_resumen(String solicitud_resumen) {
		this.solicitud_resumen = solicitud_resumen;
	}
	public int getNormativa_id() {
		return normativa_id;
	}
	public void setNormativa_id(int normativa_id) {
		this.normativa_id = normativa_id;
	}
	public InputStream getSolicitud_file() {
		return solicitud_file;
	}
	public void setSolicitud_file(InputStream solicitud_file) {
		this.solicitud_file = solicitud_file;
	}
}
