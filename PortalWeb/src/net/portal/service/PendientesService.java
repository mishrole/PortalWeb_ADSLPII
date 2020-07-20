package net.portal.service;

import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.PendientesDAO;

public class PendientesService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	PendientesDAO daoPendientes = fabrica.getPendientesDAO();
	
	public List<ListarSolicitudes> listarPendientes() {
		return daoPendientes.listPendientes();
	}
	
	public int actualizarPendiente(ListarSolicitudes bean) {
		return daoPendientes.updatePendiente(bean);
	}
	
	public NuevaSolicitud buscarPendiente(int codigo) {
		return daoPendientes.findSolicitud(codigo);
	}
	
	public int rechazarPendiente(ListarSolicitudes bean) {
		return daoPendientes.rejectPendiente(bean);
	}
}
