package net.portal.service;

import java.util.List;

import net.portal.entidad.Rol;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.RolDAO;

public class RolService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	RolDAO daoRol = fabrica.getRolDAO();
	
	public int guardarRol(Rol bean) {
		return daoRol.insertRol(bean);
	}
	
	public int actualizarRol(Rol bean) {
		return daoRol.updateRol(bean);
	}
	
	public int eliminarRol(int codigo) {
		return daoRol.deleteRol(codigo);
	}
	
	public List<Rol> listarRoles() {
		return daoRol.listRoles();
	}
}
