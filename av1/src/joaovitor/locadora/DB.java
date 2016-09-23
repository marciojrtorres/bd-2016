package joaovitor.locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	static {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			 throw new ExceptionInInitializerError(e);
		}
	}
	public Connection getConnection (){
		try {

			//conectar
			
			String url = "jdbc:postgresql://localhost/locadora";
			String usuario = "postgres";
			String senha = "123";
			Connection con = DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conectado");
			return con;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
	
}