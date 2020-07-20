package net.portal.interfaces;

import java.util.List;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;

public interface PendientesDAO {
	public List<ListarSolicitudes> listPendientes();
	public int updatePendiente(ListarSolicitudes bean);
	public NuevaSolicitud findSolicitud(int codigo);
}
