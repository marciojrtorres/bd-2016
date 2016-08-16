package agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import agenda.DB;
import agenda.model.Contato;
import agenda.model.Telefone;

public class ContatoDAO {
	
	private DB db = new DB();
	
	public void insert(Contato c) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO contatos "
					+ "(nome, sobrenome) VALUES "
					+ "(?, ?);";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getSobrenome());
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				c.setId(key.getInt(1));
			}
			// fim pegar chave
			
			// inserindo os telefones
			String sqlTelefone = 
					"INSERT INTO telefones VALUES (?,?,?)";
			for (Telefone t : c.getTelefones()) {
				PreparedStatement cmdTel = 
						con.prepareStatement(sqlTelefone);
				
				cmdTel.setInt(1, c.getId());
				cmdTel.setString(2, t.getDdd());
				cmdTel.setString(3, t.getNumero());
				
				cmdTel.execute();
			}
			//
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}











