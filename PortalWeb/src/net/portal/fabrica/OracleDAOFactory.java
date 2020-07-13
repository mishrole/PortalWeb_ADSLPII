package net.portal.fabrica;

import net.portal.dao.MySqlNormativaDAO;
import net.portal.dao.MySqlUsuarioDAO;
import net.portal.interfaces.NormativaDAO;
import net.portal.interfaces.SolicitudDAO;
import net.portal.interfaces.UsuarioDAO;

public class OracleDAOFactory extends DAOFactory {

	@Override
	public SolicitudDAO getSolicitudDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySqlUsuarioDAO();
	}

	@Override
	public NormativaDAO getNormativaDAO() {
		// TODO Auto-generated method stub
		return new MySqlNormativaDAO();
	}


}
