package net.portal.service;

import java.util.List;

import net.portal.entidad.Portal;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.PortalDAO;

public class PortalService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	PortalDAO daoPortal = fabrica.getPortalDAO();
	
	public List<Portal> listarPortal() {
		return daoPortal.listPortal();
	}
}
