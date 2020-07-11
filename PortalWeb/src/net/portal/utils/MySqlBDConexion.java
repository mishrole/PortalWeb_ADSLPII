package net.portal.utils;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySqlBDConexion {
	public static Connection getConexion(){
		Connection cn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost/portalweb","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;	
	}
}


