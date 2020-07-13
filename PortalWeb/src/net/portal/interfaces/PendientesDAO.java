package net.portal.interfaces;

import java.util.List;
import net.portal.entidad.ListarSolicitudes;

public interface PendientesDAO {
	public List<ListarSolicitudes> listPendientes();
	public int updatePendiente(ListarSolicitudes bean);
}
