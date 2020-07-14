package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.ListarSolicitudes;
import net.portal.interfaces.PendientesDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlPendientesDAO implements PendientesDAO{

	@Override
	public List<ListarSolicitudes> listPendientes() {
		List<ListarSolicitudes> lista = new ArrayList<ListarSolicitudes>();
		
		ListarSolicitudes bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select S.solicitud_id, S.solicitud_fecha, S.usuario_id, E.estado_nombre from solicitud S inner join estado E on S.estado_id = E.estado_id where S.estado_id like 1";
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
	public int updatePendiente(ListarSolicitudes bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Update solicitud set estado_id = ? where solicitud_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(bean.getEstado()));
			pstm.setInt(2, bean.getId());
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
