package beatriz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beatriz.DB;
import beatriz.model.Escritor;

//CRUD
//CREATE / READ   / UPDATE / DELETE
//INSERT / SELECT / UPDATE / DELETE

public class EscritorDAO { // Data Access Object
	
	private static final int LIMIT = 2;
	
	private DB db = new DB();
	
	public void insert(Escritor escritor) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO escritores "
					+ "(nome, sobrenome, cep) VALUES "
					+ "(?, ?, ?);";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, escritor.getNome());
			cmd.setString(2, escritor.getSobrenome());
			cmd.setInt(3, escritor.getCep());
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				escritor.setId(key.getInt(1));
			}
			// fim pegar chave
			
		/*	// inserindo os telefones
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
			
			*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
/*
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM contatos WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Contato select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome, sobrenome "
					   + "FROM contatos WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Contato contato = new Contato();
				contato.setId(id);
				contato.setNome(rs.getString("nome"));
				contato.setSobrenome(rs.getString("sobrenome"));
				return contato;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, sobrenome "
					   + "FROM contatos ORDER BY id DESC";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Contato> contatos = new ArrayList();
			
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setSobrenome(rs.getString("sobrenome"));
				contatos.add(contato);
			}
			
			return contatos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, sobrenome "
					   + "FROM contatos "
					   + "ORDER BY nome, sobrenome DESC "
					   + "OFFSET ? "
					   + "LIMIT " + LIMIT;
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			// 1 - 1 = 0 * 2 = 0
			// 2 - 1 = 1 * 2 = 2
			// 3 - 1 = 2 * 2 = 4
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Contato> contatos = new ArrayList();
			
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setSobrenome(rs.getString("sobrenome"));
				contatos.add(contato);
			}
			
			return contatos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



	
	public void selectTelefones(Contato contato) {
		if (contato.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode carregar telefones");
		}
		try (Connection con = db.getConnection()) {
			String sql = "SELECT ddd, numero "
					   + "FROM telefones WHERE id_contato = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			
			cmd.setInt(1, contato.getId());
			
			ResultSet rs = cmd.executeQuery();
			
			while (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setDdd(rs.getString("ddd"));
				telefone.setNumero(rs.getString("numero"));
				contato.addTelefone(telefone);
			}
						
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public boolean delete(Contato c) {
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return delete(c.getId());
	}
	
	public void update(Contato contato) {
		if (contato.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE contatos "
			   		   + "SET nome = ?, sobrenome = ? "
					   + "WHERE id = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, contato.getNome());
			cmd.setString(2, contato.getSobrenome());
			cmd.setInt(3, contato.getId());
			cmd.execute();
			
			sql = "DELETE FROM telefones "
				+ "WHERE id_contato = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, contato.getId());
			cmd.execute();
			
			// reinserindo
			sql = "INSERT INTO telefones VALUES (?,?,?)";
			for (Telefone t : contato.getTelefones()) {
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, contato.getId());
				cmd.setString(2, t.getDdd());
				cmd.setString(3, t.getNumero());
				cmd.execute();
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	*/
	
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM escritores WHERE id_escritor = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Escritor escritor) {
		if (escritor.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE escritores "
			   		   + "SET nome = ?, sobrenome = ?, cep = ? "
					   + "WHERE id_escritor = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, escritor.getNome());
			cmd.setString(2, escritor.getSobrenome());
			cmd.setInt(3, escritor.getId());
			cmd.setInt(4, escritor.getCep());
			cmd.execute();
			/*
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, escritor.getId());
			cmd.execute();
			*/
				
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Escritor> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_escritor, nome, sobrenome, cep "
					   + "FROM escritores ORDER BY id_escritor DESC";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Escritor> escritores = new ArrayList();
			
			while (rs.next()) {
				Escritor escritor = new Escritor();
				escritor.setId(rs.getInt("id_escritor"));
				escritor.setNome(rs.getString("nome"));
				escritor.setSobrenome(rs.getString("sobrenome"));
				escritor.setCep(rs.getInt("cep"));
				escritores.add(escritor);
			}
			
			return escritores;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Escritor select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome, sobrenome, cep "
					   + "FROM escritores WHERE id_escritor = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Escritor escritor = new Escritor();
				escritor.setId(id);
				escritor.setNome(rs.getString("nome"));
				escritor.setSobrenome(rs.getString("sobrenome"));
				escritor.setCep(rs.getInt("cep"));
				return escritor;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}