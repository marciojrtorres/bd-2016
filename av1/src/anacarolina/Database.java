package anacarolina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	
	
	static {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection(){
		
		
		try {
			
			
			String url = "jdbc:postgresql://localhost/academico";
			String usuario = "postgres";
			String senha = "postgres";
			
			Connection con = DriverManager.getConnection(url, usuario, senha);
			return con;
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		}
	
	
	}
}
