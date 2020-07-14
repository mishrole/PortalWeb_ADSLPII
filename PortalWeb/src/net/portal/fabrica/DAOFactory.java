package net.portal.fabrica;

import net.portal.interfaces.EstadoDAO;
import net.portal.interfaces.NormativaDAO;
import net.portal.interfaces.PendientesDAO;
import net.portal.interfaces.SolicitudDAO;
import net.portal.interfaces.UsuarioDAO;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int DB2 = 3;
    public static final int SQLSERVER = 4;
    public static final int XML = 5;
    
    // Registro de DAOs
    
    public abstract SolicitudDAO getSolicitudDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract NormativaDAO getNormativaDAO();
    public abstract PendientesDAO getPendientesDAO();
    public abstract EstadoDAO getEstadoDAO();
    
    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
       	case MYSQL:
        	   return new MySqlDAOFactory();
        	case XML:
        	    //return new XmlDAOFactory();
        	case ORACLE:
        	    return new OracleDAOFactory();
        	/*case DB2:
        	    return new Db2DAOFactory();*/
        	case SQLSERVER:
        	    return new SqlServerDAOFactory();
        	/*case XML:
        	    return new XmlDAOFactory();*/
        	default:
        	    return null;
        }
     }
}
