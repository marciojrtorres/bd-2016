package laurad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import laurad.DB;

public class InicializaDAO {
	
	private DB db = new DB();

	public void deleteAll() {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM usuarios_jogos";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.execute();
			sql = "DELETE FROM usuarios";
			cmd = con.prepareStatement(sql);
			cmd.execute();
			sql = "DELETE FROM jogos";
			cmd = con.prepareStatement(sql);
			cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
	
	
}
