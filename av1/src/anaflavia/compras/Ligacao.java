package anaflavia.compras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ligacao {

	public static void main(String[] args) {

		try{
			//carregar o Driver
			Class.forName("org.postgresql.Driver");
			//conectando
			String url= "jdbc:postgresql://localhost/trabalho1";
			String usuario ="postgres";
			String senha="11030226";
			Connection con = DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conectado!");
		}catch (ClassNotFoundException e){
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static {
		//carregar o Driver
		try{
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e){
			throw new ExceptionInInitializerError(e);
		}

	}
	
	public Connection getConnection(){
		try{			
			//conectando									
			String url= "jdbc:postgresql://localhost/trabalho1";
			String usuario ="postgres";
			String senha="11030226";
			Connection con = DriverManager.getConnection(url,usuario,senha);
			
			return con;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
	

