package jeniffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jeniffer.controlador.DB;
import jeniffer.model.Aluno;
import jeniffer.model.Serie;

public class VicioDAO{
	
	private static final int LIMIT = 2;
	private DB db = new DB();
	
	public void insert(Aluno aluno) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO alunos "
					+ "(nome, genero_user, aniversario) VALUES "
					+ "(?,genero (?),?);";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, aluno.getNome());
			cmd.setString(2, aluno.getGenero().toString());
			cmd.setDate(3, aluno.getAniversario());
			
			cmd.execute();
			
			ResultSet key = cmd.getGeneratedKeys();
			
			if (key.next()) {
				aluno.setId(key.getInt(1));
			}
			
			String sqlSerie = 
					"INSERT INTO series VALUES (?,?,?,?)";
			for (Serie s : aluno.getSeries()) {
				PreparedStatement cmdSerie = con.prepareStatement(sqlSerie);
				
				cmdSerie.setInt(1, aluno.getId());
				cmdSerie.setString(2, s.getNome());
				cmdSerie.setInt(3, s.getTemporada());
				cmdSerie.setInt(4, s.getEpisodio());
				
				cmdSerie.execute();
			}
			//
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM alunos WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			if (cmd == null){
				String sqlSerie = "DELETE FROM series WHERE id = ?";
				cmd.setInt(1, id);	
			}
			
			return cmd.execute();
			
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Aluno select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome, aniversario "
					   + "FROM alunos WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery(); //
			
			if (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(id);
				aluno.setNome(rs.getString("nome"));
				aluno.setAniversario(rs.getDate("aniversario"));
				return aluno;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, aniversario "
					   + "FROM alunos ORDER BY id DESC";
			PreparedStatement cmd = con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Aluno> alunos = new ArrayList();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setAniversario(rs.getDate("aniversario"));
				alunos.add(aluno);
			}
			
			return alunos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Aluno> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, aniversario "
					   + "FROM alunos "
					   + "ORDER BY nome, aniversario DESC "
					   + "OFFSET ? "
					   + "LIMIT " + LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);
			// 1 - 1 = 0 * 2 = 0
			// 2 - 1 = 1 * 2 = 2
			// 3 - 1 = 2 * 2 = 4
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Aluno> alunos = new ArrayList();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setAniversario(rs.getDate("aniversario"));
				alunos.add(aluno);
			}
			
			return alunos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void selectSeries(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode carregar telefones");
		}
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome, temporada, episodio "
					   + "FROM series WHERE id_aluno = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setInt(1, aluno.getId());
			
			ResultSet rs = cmd.executeQuery();
			
			while (rs.next()) {
				Serie serie = new Serie();
				serie.setNome(rs.getString("nome"));
				serie.setTemporada(rs.getInt("temporada"));
				serie.setEpisodio(rs.getInt("episodio"));
				aluno.addSerie(serie);
			}
						
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return delete(aluno.getId());
	}
	
	public void update(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE alunos "
			   		   + "SET nome = ?, genero_user = ?, aniversario = ? "
					   + "WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, aluno.getNome());
			cmd.setString(2, aluno.getGenero().toString());
			cmd.setDate(3, aluno.getAniversario());
			cmd.setInt(4, aluno.getId());
			cmd.execute();
			
			sql = "DELETE FROM series "
				+ "WHERE id_contato = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, aluno.getId());
			cmd.execute();
			
			// reinserindo
			sql = "INSERT INTO series VALUES (?,?,?,?)";
			for (Serie serie : aluno.getSeries()) {
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, aluno.getId());
				cmd.setString(2, serie.getNome());
				cmd.setInt(3, serie.getTemporada());
				cmd.setInt(3, serie.getEpisodio());
				cmd.execute();
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Serie> selectByNome(String nome){
		try (Connection con = db.getConnection()) {
			String sql = "SELECT temporada, episodio "
					   + "FROM series WHERE nome = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			//cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery(); //
			
			if (rs.next()) {
				Serie serie = new Serie();
				serie.setNome(nome);
				serie.setTemporada(rs.getInt("temporada"));
				serie.setEpisodio(rs.getInt("episodio"));
				return (List<Serie>) serie;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}