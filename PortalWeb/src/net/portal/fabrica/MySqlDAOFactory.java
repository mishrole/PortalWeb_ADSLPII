package net.portal.fabrica;

import net.portal.dao.MySqlEstadoDAO;
import net.portal.dao.MySqlIformeDAO;
import net.portal.dao.MySqlNormativaDAO;
import net.portal.dao.MySqlPendientesDAO;
import net.portal.dao.MySqlPortalDAO;
import net.portal.dao.MySqlPublicacionesDAO;
import net.portal.dao.MySqlRolDAO;
import net.portal.dao.MySqlSolicitudDAO;
import net.portal.dao.MySqlUsuarioDAO;
import net.portal.interfaces.EstadoDAO;
import net.portal.interfaces.InformeDAO;
import net.portal.interfaces.NormativaDAO;
import net.portal.interfaces.PendientesDAO;
import net.portal.interfaces.PortalDAO;
import net.portal.interfaces.PublicacionesDAO;
import net.portal.interfaces.RolDAO;
import net.portal.interfaces.SolicitudDAO;
import net.portal.interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public SolicitudDAO getSolicitudDAO() {
		return new MySqlSolicitudDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySqlUsuarioDAO();
	}

	@Override
	public NormativaDAO getNormativaDAO() {
		return new MySqlNormativaDAO();
	}

	@Override
	public PendientesDAO getPendientesDAO() {
		return new MySqlPendientesDAO();
	}

	@Override
	public EstadoDAO getEstadoDAO() {
		return new MySqlEstadoDAO();
	}

	@Override
	public RolDAO getRolDAO() {
		return new MySqlRolDAO();
	}

	@Override
	public PublicacionesDAO getPublicacionesDAO() {
		return new MySqlPublicacionesDAO();
	}

	@Override
	public InformeDAO getInformeDAO() {
		return new MySqlIformeDAO();
	}

	@Override
	public PortalDAO getPortalDAO() {
		return new MySqlPortalDAO();
	}
}
