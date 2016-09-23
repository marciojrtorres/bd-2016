package eduarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eduarda.*;
import eduarda.model.Curso;

public class CursoDAO {
	
	private DB db = new DB();
	
	private static final int LIMIT = 2;

	
	public void insert(Curso c) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO cursos VALUES(DEFAULT, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, c.getNome());
			cmd.setInt(2, c.getDuracao());
			
			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				c.setId(key.getInt(1));
			}


		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM cursos WHERE id = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Curso select(int id) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT nome, duracao FROM cursos WHERE id = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Curso curso = new Curso();
				curso.setId(id);
				curso.setNome(rs.getString("nome"));
				curso.setDuracao(rs.getInt("duracao"));
				return curso;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Curso select(String nome) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, duracao FROM cursos WHERE nome = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setString(1, nome);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setNome(nome);
				curso.setDuracao(rs.getInt("duracao"));
				return curso;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, duracao FROM cursos ORDER BY id DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); 

			List<Curso> cursos = new ArrayList();
			
			while(rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setNome(rs.getString("nome"));
				curso.setDuracao(rs.getInt("duracao"));
				cursos.add(curso);
			}
			return cursos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> selectPage(int pagina) {
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, duracao FROM cursos ORDER BY nome DESC OFFSET ? LIMIT 2";
			

			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); 

			List<Curso> cursos = new ArrayList();
			
			while(rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setNome(rs.getString("nome"));
				curso.setDuracao(rs.getInt("duracao"));
				cursos.add(curso);
			}
			return cursos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Curso curso) {
		if (curso.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE cursos "
			   		   + "SET nome = ?, duracao = ? WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, curso.getNome());
			cmd.setInt(2, curso.getDuracao());
			cmd.setInt(3, curso.getId());
			

			cmd.execute();
			
			sql = "DELETE FROM cursos WHERE id = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, curso.getId());
			cmd.execute();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
