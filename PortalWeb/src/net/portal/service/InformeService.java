package net.portal.service;

import java.util.List;

import net.portal.entidad.Informe;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.InformeDAO;

public class InformeService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	InformeDAO daoInforme = fabrica.getInformeDAO();
	
	public int guardarInforme(Informe bean) {
		return daoInforme.insertInforme(bean);
	}
	
	public List<ListarSolicitudes> listarInformesPendientes() {
		return daoInforme.listInformesPendientes();
	}
	
	public NuevaSolicitud buscarSolicitud(int codigo) {
		return daoInforme.findSolicitud(codigo);
	}
	
	public int actualizarEstadoSolicitud(ListarSolicitudes bean) {
		return daoInforme.updateEstadoSolicitud(bean);
	}
	
	public Informe buscarInforme(int codigo) {
		return daoInforme.findInforme(codigo);
	}
	
}
