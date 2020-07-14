package net.portal.service;

import java.util.List;

import net.portal.entidad.Estado;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.EstadoDAO;

public class EstadoService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	EstadoDAO daoEstado = fabrica.getEstadoDAO();
	
	public int guardarEstado(Estado bean) {
		return daoEstado.insertEstado(bean);
	}
	
	public int actualizarEstado(Estado bean) {
		return daoEstado.updateEstado(bean);
	}
	
	public int eliminarEstado(int codigo) {
		return daoEstado.deleteEstado(codigo);
	}
	
	public List<Estado> listarEstados() {
		return daoEstado.listEstados();
	}
}
