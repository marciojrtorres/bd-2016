package victor.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	static{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Bands", "postgres", "projeto_de_pesquisa");
			return con;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
}
