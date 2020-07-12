package net.portal.fabrica;

import net.portal.dao.MySqlSolicitudDAO;
import net.portal.interfaces.SolicitudDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public SolicitudDAO getSolicitudDAO() {
		// TODO Auto-generated method stub
		return new MySqlSolicitudDAO();
	}

}
