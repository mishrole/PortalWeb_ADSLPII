package net.portal.fabrica;

import net.portal.dao.MySqlEstadoDAO;
import net.portal.dao.MySqlNormativaDAO;
import net.portal.dao.MySqlPendientesDAO;
import net.portal.dao.MySqlSolicitudDAO;
import net.portal.dao.MySqlUsuarioDAO;
import net.portal.interfaces.EstadoDAO;
import net.portal.interfaces.NormativaDAO;
import net.portal.interfaces.PendientesDAO;
import net.portal.interfaces.SolicitudDAO;
import net.portal.interfaces.UsuarioDAO;

public class OracleDAOFactory extends DAOFactory {

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


}
