package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDBConexion {
	static{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");//Version 8
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConexion(){
		Connection con=null;
		try {
			//la conex�n de conexi�n
			//La ip, puerto, base de datos, usuario, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas?serverTimezone=UTC","root","mysql");           
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}