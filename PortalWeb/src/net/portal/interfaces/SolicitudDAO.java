package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;

public interface SolicitudDAO {
	public int insertSolicitud(NuevaSolicitud bean);
	public int delete(int codigo);
	public int update(NuevaSolicitud bean);
	public List<ListarSolicitudes> listPresentadas();
	public NuevaSolicitud findSolicitud(int codigo);

}
