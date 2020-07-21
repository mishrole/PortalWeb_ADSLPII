package net.portal.service;

import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.PublicacionesDAO;

public class PublicacionesService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	PublicacionesDAO daoPublicaciones = fabrica.getPublicacionesDAO();
	
	public List<ListarSolicitudes> listarPublicaciones() {
		return daoPublicaciones.listPublicacionesPendientes();
	}
	
	public int actualizarPublicacion(ListarSolicitudes bean) {
		return daoPublicaciones.updatePublicacionesPendientes(bean);
	}
	
	public NuevaSolicitud buscarPublicacion(int codigo) {
		return daoPublicaciones.findPublicacion(codigo);
	}
}
