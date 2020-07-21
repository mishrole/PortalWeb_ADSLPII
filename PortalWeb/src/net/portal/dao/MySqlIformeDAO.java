package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Informe;
import net.portal.entidad.ListarSolicitudes;
import net.portal.entidad.NuevaSolicitud;
import net.portal.interfaces.InformeDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlIformeDAO implements InformeDAO{

	@Override
	public int insertInforme(Informe bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into informe values(null, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getInforme_fecha());
			pstm.setInt(2, bean.getSolicitud_id());
			pstm.setInt(3, bean.getUsuario_id());
			pstm.setString(4, bean.getInforme_argumento());
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
	public List<ListarSolicitudes> listInformesPendientes() {
		List<ListarSolicitudes> lista = new ArrayList<ListarSolicitudes>();
		
		ListarSolicitudes bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, E.estado_nombre from solicitud S inner join estado E on S.estado_id = E.estado_id where S.estado_id = 3 or S.estado_id = 4";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new ListarSolicitudes();
				bean.setId(rs.getInt(1));
				bean.setFecha(""+rs.getDate(2));
				bean.setEstado(rs.getString(3));
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
	public NuevaSolicitud findSolicitud(int codigo) {
		NuevaSolicitud bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.solicitud_nombre, "
					+ "S.solicitud_resumen, N.normativa_nombre, S.usuario_id, S.tecnico_asignado, E.estado_nombre from solicitud S"
					+ " inner join normativa N on S.normativa_id = N.normativa_id inner join estado E on S.estado_id = E.estado_id"
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
				bean.setUsuario(rs.getInt(6));
				bean.setTecnico(rs.getInt(7));
				bean.setEstado(rs.getString(8));
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
	public int updateEstadoSolicitud(ListarSolicitudes bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Update solicitud set estado_id = 5 where solicitud_id = ?";
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
	public Informe findInforme(int codigo) {
		Informe bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select * from informe where solicitud_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			rs = pstm.executeQuery();

			if(rs.next()) {
				bean = new Informe();
				bean.setInforme_id(rs.getInt(1));
				bean.setInforme_fecha(rs.getString(2));
				bean.setSolicitud_id(rs.getInt(3));
				bean.setUsuario_id(rs.getInt(4));
				bean.setInforme_argumento(rs.getString(5));
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
}
