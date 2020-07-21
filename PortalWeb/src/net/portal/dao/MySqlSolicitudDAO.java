package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.NuevaSolicitud;
import net.portal.interfaces.SolicitudDAO;
import net.portal.entidad.ListarSolicitudes;
import net.portal.utils.MySqlBDConexion;

public class MySqlSolicitudDAO implements SolicitudDAO{

	@Override
	public int insertSolicitud(NuevaSolicitud bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into solicitud values(null, ?, ?, ?, ?, ?, ?, ?, null)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getFecha());
			pstm.setInt(2, bean.getUsuario());
			pstm.setInt(3, Integer.parseInt(bean.getEstado()));
			pstm.setString(4, bean.getSolicitud_nombre());
			pstm.setString(5, bean.getSolicitud_resumen());
			pstm.setInt(6, Integer.parseInt(bean.getNormativa_id()));
			pstm.setBlob(7, bean.getSolicitud_file());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	public List<ListarSolicitudes> listPresentadas(int codigo) {
		List<ListarSolicitudes> lista = new ArrayList<ListarSolicitudes>();
		
		ListarSolicitudes bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.usuario_id, E.estado_nombre from solicitud S inner join estado E on S.estado_id = E.estado_id where S.usuario_id = ? order by S.solicitud_fecha asc";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
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
		}finally {
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
	public NuevaSolicitud findSolicitud(int codigo) {
		NuevaSolicitud bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.solicitud_nombre, S.solicitud_resumen, N.normativa_nombre,"
					+ " E.estado_nombre, S.solicitud_file, S.tecnico_asignado from solicitud S inner join estado E on S.estado_id = E.estado_id"
					+ " inner join normativa N on S.normativa_id = N.normativa_id inner join usuario U on U.usuario_id = S.tecnico_asignado where solicitud_id = ?";
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
				bean.setEstado(rs.getString(6));
				bean.setSolicitud_file(rs.getBinaryStream(7));
				bean.setTecnico(rs.getInt(8));
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
				
		return bean;	
	}
}
