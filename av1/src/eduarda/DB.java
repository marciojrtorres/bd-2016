package eduarda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
		static {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e){
				throw new ExceptionInInitializerError(e);
			}
		}
		
		public Connection getConnection() {
			try{
				String url = "jdbc:postgresql://localhost/escola";
				String usuario = "postgres";
				String senha = "11030178";
				Connection con = DriverManager.getConnection(url, usuario, senha);
				System.out.println("conectado");
				return con;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} 
		}
}