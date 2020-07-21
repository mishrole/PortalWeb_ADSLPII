package net.portal.interfaces;

import java.util.List;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.entidad.Portal;

public interface PublicacionesDAO {
	public int insertPublicacion(Portal bean);
	public List<ListarSolicitudes> listPublicacionesPendientes(int codigo);
	public int updatePublicacionesPendientes(ListarSolicitudes bean);
	public NuevaSolicitud findPublicacion(int codigo);
}
