package net.portal.interfaces;

import java.util.List;

import net.portal.entidad.Rol;

public interface RolDAO {
	public int insertRol(Rol bean);
	public int updateRol(Rol bean);
	public int deleteRol(int codigo);
	public List<Rol> listRoles();
}
