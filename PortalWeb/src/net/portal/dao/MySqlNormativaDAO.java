package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Normativa;
import net.portal.interfaces.NormativaDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlNormativaDAO implements NormativaDAO{

	@Override
	public int insertNormativa(Normativa bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into normativa values(null, ?)";
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getNormativa_nombre());
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
	public int updateNormativa(Normativa bean) {
		int estado=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update tb_docente set normativa_nombre = ? where normativa_id = ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getNormativa_nombre());
			pstm.setInt(2, bean.getNormativa_id());
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
	public int deleteNormativa(int codigo) {
		int estado=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="delete from normativa where normativa_id = ?";
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
	public List<Normativa> listNormativas() {
		List<Normativa> lista = new ArrayList<Normativa>();
		
		Normativa bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select normativa_id, normativa_nombre from normativa";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Normativa();
				bean.setNormativa_id(rs.getInt(1));
				bean.setNormativa_nombre(rs.getString(2));
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
