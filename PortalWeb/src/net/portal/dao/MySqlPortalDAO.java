package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Portal;
import net.portal.interfaces.PortalDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlPortalDAO implements PortalDAO{

	@Override
	public List<Portal> listPortal() {
		List<Portal> lista = new ArrayList<Portal>();
		
		Portal bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select * from portal";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Portal();
				bean.setPortal_id(rs.getInt(1));
				bean.setPortal_fecha(rs.getString(2));
				bean.setSolicitud_id(rs.getInt(3));
				bean.setUsuario_id(rs.getInt(4));
				bean.setVisibilidad_id(rs.getInt(5));
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
