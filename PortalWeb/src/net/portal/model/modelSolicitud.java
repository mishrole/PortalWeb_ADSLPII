package net.portal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import net.portal.entidad.Solicitud;
import net.portal.utils.MySqlBDConexion;

public class modelSolicitud {

	public int registrarSolicitud(Solicitud bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into solicitud values(null, ?, ?, ?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getSolicitud_fecha());
			pstm.setInt(2, bean.getUsuario_id());
			pstm.setInt(3, bean.getEstado_id());
			pstm.setString(4, bean.getSolicitud_nombre());
			pstm.setString(5, bean.getSolicitud_resumen());
			pstm.setInt(6, bean.getNormativa_id());
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
}
