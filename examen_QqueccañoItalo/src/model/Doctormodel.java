package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Doctor;
import util.MySqlDBConexion;

public class Doctormodel {
	
	public static final Log log = LogFactory.getLog(Doctormodel.class);
	public int insertaDoctor(Doctor obj) {
		int salida=-1;
		Connection conn=null;
		PreparedStatement psmt = null;
		try { 
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into doctor values(null,?,?,??,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, obj.getNombre());
			psmt.setString(2, obj.getDni());
			psmt.setDate(3,obj.getFecha());
			psmt.setDouble(4, obj.getSueldo());
			psmt.setString(5, obj.getCorreo());
			
			log.info(psmt);
			
			salida = psmt.executeUpdate();
		} catch (Exception e) {  
			e.printStackTrace();
			
		}finally {
			try {
				if(psmt !=null) psmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				
			}
			
		}
		
		
		return salida;
	}
}
