package rai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rai.agencia.DB;
import rai.model.Empresario;

public class EmpresarioDao {
private static final int LIMIT = 2;
	
	private DB db = new DB();
	public void insert(Empresario emp) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO empresarios "
					+ "(nome,contato) VALUES "
					+ "(?, ?);";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, emp.getNome());
			cmd.setString(2, emp.getContato());
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				emp.setId(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM empresarios WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Empresario select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome, sobrenome "
					   + "FROM empresarios WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet res = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (res.next()) {
				Empresario empresario = new Empresario();
				empresario.setId(id);
				empresario.setNome(res.getString("nome"));
				empresario.setContato(res.getString("contato"));
				return empresario;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Empresario> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, contato "
					   + "FROM empresarios "
					   + "ORDER BY nome, contato DESC "
					   + "OFFSET ? "
					   + "LIMIT " + LIMIT;
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			// 1 - 1 = 0 * 2 = 0
			// 2 - 1 = 1 * 2 = 2
			// 3 - 1 = 2 * 2 = 4
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet res = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Empresario> empresarios = new ArrayList();
			
			while (res.next()) {
				Empresario empresario = new Empresario();
				empresario.setId(res.getInt("id"));
				empresario.setNome(res.getString("nome"));
				empresario.setContato(res.getString("contato"));
				empresarios.add(empresario);
			}
			
			return empresarios;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Empresario empresario) {
		if (empresario.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE empresarios "
			   		   + "SET nome = ?, contato = ? "
					   + "WHERE id = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, empresario.getNome());
			cmd.setString(2, empresario.getContato());
			cmd.setInt(3, empresario.getId());
			cmd.execute();
			
			
			
			// reinserindo
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Empresario> selectCriteriado(String nome){
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, contato "
					   + "FROM empresarios "
					   + "WHERE nome=?";
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			cmd.setString(1,nome);
			ResultSet res = cmd.executeQuery();
			List<Empresario> empresarios = new ArrayList();
			while (res.next()) {
				Empresario empresario = new Empresario();
				empresario.setId(res.getInt("id"));
				empresario.setNome(res.getString("nome"));
				empresario.setContato(res.getString("contato"));
				empresarios.add(empresario);
			}
		return empresarios;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
