package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Estado;
import net.portal.interfaces.EstadoDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlEstadoDAO implements EstadoDAO{

	@Override
	public int insertEstado(Estado bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into estado values(null, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getEstado_nombre());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public int updateEstado(Estado bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Update estado set estado_nombre = ? where estado_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getEstado_nombre());
			pstm.setInt(2, bean.getEstado_id());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public int deleteEstado(int codigo) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Delete from estado where estado_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public List<Estado> listEstados() {
		List<Estado> lista = new ArrayList<Estado>();
		
		Estado bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select estado_id, estado_nombre from estado";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Estado();
				bean.setEstado_id(rs.getInt(1));
				bean.setEstado_nombre(rs.getString(2));
				lista.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

}
