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
	public int deleteSolicitud(int codigo) {
		int estado=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="delete from solicitud where solicitud_id = ? and estado_id = 1";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,codigo);
			estado=pstm.executeUpdate();
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
	public int updateSolicitud(NuevaSolicitud bean) {
		int estado=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update solicitud set solicitud_fecha = ?, usuario_id = ?, estado_id = ?, solicitud_nombre = ?, solicitud_resumen = ? , normativa_id = ?, solicitud_file = ? where solicitud_id = ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getFecha());
			pstm.setInt(2, bean.getUsuario());
			pstm.setInt(3, Integer.parseInt(bean.getEstado()));
			pstm.setString(4, bean.getSolicitud_nombre());
			pstm.setString(5, bean.getSolicitud_resumen());
			pstm.setInt(6, Integer.parseInt(bean.getNormativa_id()));
			pstm.setBlob(7, bean.getSolicitud_file());
			pstm.setInt(8, bean.getId());
			estado=pstm.executeUpdate();
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
}
