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
			String sql = "Insert into solicitud values(null, ?, ?, ?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getFecha());
			pstm.setInt(2, Integer.parseInt(bean.getUsuario()));
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
	public int delete(int codigo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(NuevaSolicitud bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ListarSolicitudes> listPresentadas() {
		List<ListarSolicitudes> lista = new ArrayList<ListarSolicitudes>();
		
		ListarSolicitudes bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.usuario_id, E.estado_nombre" +
			" from solicitud S inner join estado E on S.estado_id = E.estado_id";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new ListarSolicitudes();
				bean.setId(rs.getInt(1));
				bean.setFecha(rs.getString(2));
				bean.setUsuario(rs.getString(3));
				bean.setEstado(rs.getString(4));
				lista.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) rs.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public NuevaSolicitud findSolicitud(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
}
