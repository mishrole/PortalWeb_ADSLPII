package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Normativa;

public interface NormativaDAO {
	
	public int insertNormativa(Normativa bean);
	public int updateNormativa(Normativa bean);
	public int deleteNormativa(int codigo);
	public List<Normativa> listNormativas();

}
