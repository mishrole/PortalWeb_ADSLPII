package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Estado;

public interface EstadoDAO {
	public int insertEstado(Estado bean);
	public int updateEstado(Estado bean);
	public int deleteEstado(int codigo);
	public List<Estado> listEstados();
}
