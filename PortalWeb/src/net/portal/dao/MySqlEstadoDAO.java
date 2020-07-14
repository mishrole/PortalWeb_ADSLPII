package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		return estado;
	}

	@Override
	public int deleteEstado(int codigo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Estado> listEstados() {
		// TODO Auto-generated method stub
		return null;
	}

}
