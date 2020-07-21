package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.entidad.Portal;
import net.portal.interfaces.PublicacionesDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlPublicacionesDAO implements PublicacionesDAO{

	@Override
	public List<ListarSolicitudes> listPublicacionesPendientes() {
		List<ListarSolicitudes> lista = new ArrayList<ListarSolicitudes>();
		
		ListarSolicitudes bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.usuario_id, E.estado_nombre from solicitud S inner join estado E on S.estado_id = E.estado_id where S.estado_id like 2";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new ListarSolicitudes();
				bean.setId(rs.getInt(1));
				bean.setFecha(""+rs.getDate(2));
				bean.setUsuario(rs.getInt(3));
				bean.setEstado(rs.getString(4));
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

	@Override
	public int updatePublicacionesPendientes(ListarSolicitudes bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Update solicitud set estado_id = 4 where solicitud_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getId());
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
	public NuevaSolicitud findPublicacion(int codigo) {
		NuevaSolicitud bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.solicitud_nombre, "
					+ "S.solicitud_resumen, N.normativa_nombre from solicitud S"
					+ " inner join normativa N on S.normativa_id = N.normativa_id"
					+ " where solicitud_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			rs = pstm.executeQuery();

			if(rs.next()) {
				bean = new NuevaSolicitud();
				bean.setId(rs.getInt(1));
				bean.setFecha(rs.getString(2));
				bean.setSolicitud_nombre(rs.getString(3));
				bean.setSolicitud_resumen(rs.getString(4));
				bean.setNormativa_id(rs.getString(5));
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
		
		return bean;
	}

	@Override
	public int insertPublicacion(Portal bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into portal values(null, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getPortal_fecha());
			pstm.setInt(2, bean.getSolicitud_id());
			pstm.setInt(3, bean.getUsuario_id());
			pstm.setInt(4, bean.getVisibilidad_id());
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
}
