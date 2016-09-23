package vinicius;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	//Construtor est�tico
		// Acontece uma �nica vez no primeiro uso da classe
		static{
			//Carregar o Driver
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				throw new ExceptionInInitializerError(e);
			}
		}
		public Connection getConnection(){
				try {
					String url = "jdbc:postgresql://localhost/empresa";
					String usuario = "postgres";
					String senha = "1234";
					Connection con = DriverManager.getConnection(url, usuario, senha);
					return con;
				}catch (SQLException e) {
					throw new RuntimeException(e);
				}	
		}
}
