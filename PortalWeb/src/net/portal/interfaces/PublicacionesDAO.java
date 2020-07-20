package net.portal.interfaces;

import java.util.List;
import net.portal.entidad.ListarSolicitudes;

public interface PublicacionesDAO {
	public List<ListarSolicitudes> listPublicacionesPendientes();
	public int updatePublicacionesPendientes(ListarSolicitudes bean);
}
