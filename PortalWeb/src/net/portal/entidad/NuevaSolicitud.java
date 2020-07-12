package net.portal.entidad;

import java.io.InputStream;
import java.util.Date;

public class NuevaSolicitud extends ListarSolicitudes{
	private String solicitud_nombre;
	private String solicitud_resumen;
	private String normativa_id;
	private InputStream solicitud_file;
	
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
	public String getNormativa_id() {
		return normativa_id;
	}
	public void setNormativa_id(String normativa_id) {
		this.normativa_id = normativa_id;
	}
	public InputStream getSolicitud_file() {
		return solicitud_file;
	}
	public void setSolicitud_file(InputStream solicitud_file) {
		this.solicitud_file = solicitud_file;
	}
	
}
