package net.portal.interfaces;

import java.util.List;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;

public interface PublicacionesDAO {
	public List<ListarSolicitudes> listPublicacionesPendientes();
	public int updatePublicacionesPendientes(ListarSolicitudes bean);
	public NuevaSolicitud findPublicacion(int codigo);
}
