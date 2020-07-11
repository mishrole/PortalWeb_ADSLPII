package net.portal.entidad;

import java.sql.Date;

public class Informe {
	
	private int informe_id;
	private Date informe_fecha;
	
	private int solicitud_id;
	private int usuario_id;
	
	private String informe_argumento;

	public int getInforme_id() {
		return informe_id;
	}

	public void setInforme_id(int informe_id) {
		this.informe_id = informe_id;
	}

	public Date getInforme_fecha() {
		return informe_fecha;
	}

	public void setInforme_fecha(Date informe_fecha) {
		this.informe_fecha = informe_fecha;
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

	public String getInforme_argumento() {
		return informe_argumento;
	}

	public void setInforme_argumento(String informe_argumento) {
		this.informe_argumento = informe_argumento;
	}
}
