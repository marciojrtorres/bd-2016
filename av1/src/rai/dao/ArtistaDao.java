package rai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rai.agencia.DB;
import rai.model.Artista;



public class ArtistaDao {
	private static final int LIMIT = 2;
	private DB db = new DB();
	public void insert(Artista art) {
		//artistas(id_empresario,nome,data_criacao,tip)
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO artistas "
					+ "(id_empresario,nome,data_criacao,tip) VALUES "
					+ "(?, ?, ?, ?);";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setInt(1, art.getId_empresario());
			cmd.setString(2, art.getNome());
			cmd.setString(3, art.getData_criacao());
			cmd.setObject(4, art.getTip());
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				art.setId(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM artistas WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Artista select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome,id_empresario,data_criacao_tip"
					   + "FROM artistas WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet res = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (res.next()) {
				Artista artista = new Artista();
				artista.setId(id);
				artista.setNome(res.getString("nome"));
				artista.setId_empresario(Integer.parseInt(res.getString("id_empresario")));
				artista.setData_criacao(res.getString("data_criacao"));
				artista.setTip(res.getString("tip"));
				return artista;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Artista> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id,id_empresario,nome,data_criacao,tip "
					   + "FROM artistas "
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
			
			List<Artista> artistas = new ArrayList();
			
			while (res.next()) {
				Artista artista = new Artista();
				artista.setId(res.getInt("id"));
				artista.setId_empresario(res.getInt("id_empresario"));
				artista.setNome(res.getString("nome"));
				artista.setData_criacao(res.getString("data_criacao"));
				artista.setTip(res.getString("tip"));
				artistas.add(artista);
			}
			
			return artistas;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Artista artista) {
		if (artista.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE artistas "
			   		   + "SET id_empresario= ?,nome= ?,data_criacao= ?,tip= ? "
					   + "WHERE id = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setInt(1, artista.getId_empresario());
			cmd.setString(2, artista.getNome());
			cmd.setString(3, artista.getData_criacao());
			cmd.setObject(4, artista.getTip());
			cmd.setInt(5, artista.getId());
			cmd.execute();
			
			
			
			// reinserindo
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Artista> selectCriteriado(String nome){
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_empresario,nome,data_criacao,tip "
					   + "FROM artistas "
					   + "WHERE nome=?";
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			cmd.setString(1,nome);
			ResultSet res = cmd.executeQuery();
			List<Artista> artistas = new ArrayList();
			while (res.next()) {
				Artista artista = new Artista();
				artista.setId(res.getInt("id"));
				artista.setId_empresario(res.getInt("id_empresario"));
				artista.setNome(res.getString("nome"));
				artista.setData_criacao(res.getString("data_criacao"));
				artista.setTip(res.getString("tip"));
				artistas.add(artista);
			}
		return artistas;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
