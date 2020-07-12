package net.portal.service;

import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.SolicitudDAO;

public class SolicitudService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	SolicitudDAO daoSolicitud = fabrica.getSolicitudDAO();
	
	public int registraSolicitud(NuevaSolicitud bean) {
		return daoSolicitud.insertSolicitud(bean);
	}
	
	public List<ListarSolicitudes>listarPresentadas() {
		return daoSolicitud.listPresentadas();
	}

}
