package net.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.portal.entidad.Menu;
import net.portal.entidad.Usuario;
import net.portal.interfaces.UsuarioDAO;
import net.portal.utils.MySqlBDConexion;

public class MySqlUsuarioDAO implements UsuarioDAO{

	@Override
	public Usuario iniciarSesion(String login, String password) {
		Usuario bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select * from usuario where usuario_login = ? and usuario_password = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				bean = new Usuario();
				bean.setId(rs.getInt(1));
				bean.setNombre(rs.getString(4));
				bean.setApellido(rs.getString(5));
				bean.setRol(rs.getInt(6));
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
	public List<Menu> getMenusUsuario(int id) {
		List<Menu> lista = new ArrayList<Menu>();
		Menu bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select A.menu_id, M.menu_descripcion, M.menu_url from acceso A join menu M on A.menu_id = M.menu_id where A.usuario_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Menu();
				bean.setId(rs.getInt(1));
				bean.setDescripcion(rs.getString(2));
				bean.setUrl(rs.getString(3));
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

	@Override
	public List<Usuario> listUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		Usuario bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select * from usuario";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Usuario();
				bean.setId(rs.getInt(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setApellido(rs.getString(5));
				bean.setRol(rs.getInt(6));
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
	public int insertUsuario(Usuario bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into usuario values (null, ?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getLogin());
			pstm.setString(2, bean.getPassword());
			pstm.setString(3, bean.getNombre());
			pstm.setString(4, bean.getApellido());
			pstm.setInt(5, bean.getRol());
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
	public int deleteUsuario(int codigo) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Delete from usuario where usuario_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
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
	public int updateUsuario(Usuario bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Update usuario set usuario_login = ?, usuario_password = ?, usuario_nombre = ?, usuario_apellido = ?, rol_id = ? where usuario_id like ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getLogin());
			pstm.setString(2, bean.getPassword());
			pstm.setString(3, bean.getNombre());
			pstm.setString(4, bean.getApellido());
			pstm.setInt(5, bean.getRol());
			pstm.setInt(6, bean.getId());
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
