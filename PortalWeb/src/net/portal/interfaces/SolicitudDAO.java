package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;

public interface SolicitudDAO {
	public int insertSolicitud(NuevaSolicitud bean);
	public List<ListarSolicitudes> listPresentadas(int codigo);
}
