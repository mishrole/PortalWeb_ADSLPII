package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Informe;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;

public interface InformeDAO {
	public int insertInforme(Informe bean);
	public int updateEstadoSolicitud(ListarSolicitudes bean);
	public List<ListarSolicitudes> listInformesPendientes();
	public NuevaSolicitud findSolicitud(int codigo);
	public Informe findInforme(int codigo);
}
