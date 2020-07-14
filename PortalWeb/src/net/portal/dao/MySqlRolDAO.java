package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Rol;
import net.portal.interfaces.RolDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlRolDAO implements RolDAO{

	@Override
	public int insertRol(Rol bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into rol values(null, ?)";
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getRol_nombre());
			estado = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return estado;
	}

	@Override
	public int updateRol(Rol bean) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "update rol set rol_nombre = ? where rol_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getRol_nombre());
			pstm.setInt(2, bean.getRol_id());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return estado;
	}

	@Override
	public int deleteRol(int codigo) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Delete from rol where rol_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1,codigo);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
	}

	@Override
	public List<Rol> listRoles() {
		List<Rol> lista = new ArrayList<Rol>();
		
		Rol bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select rol_id, rol_nombre from rol";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Rol();
				bean.setRol_id(rs.getInt(1));
				bean.setRol_nombre(rs.getString(2));
				lista.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

}
