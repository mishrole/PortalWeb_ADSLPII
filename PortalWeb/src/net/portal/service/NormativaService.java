package net.portal.service;

import java.util.List;

import net.portal.entidad.Normativa;
import net.portal.fabrica.DAOFactory;
import net.portal.interfaces.NormativaDAO;

public class NormativaService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	NormativaDAO daoNormativa = fabrica.getNormativaDAO();
	
	public int guardarNormativa(Normativa bean) {
		return daoNormativa.insertNormativa(bean);
	}
	
	public int actualizarNormativa(Normativa bean) {
		return daoNormativa.updateNormativa(bean);
	}
	
	public int eliminarNormativa(int codigo) {
		return daoNormativa.deleteNormativa(codigo);
	}
	
	public List<Normativa> listarNormativas() {
		return daoNormativa.listNormativas();
	}
}
