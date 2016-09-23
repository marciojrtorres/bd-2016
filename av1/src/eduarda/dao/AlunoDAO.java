package eduarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eduarda.*;
import eduarda.model.Aluno;
import eduarda.model.Genero;

public class AlunoDAO {
	
	private DB db = new DB();
	
	private static final int LIMIT = 2;
	
	public void insert(Aluno a) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO alunos VALUES(DEFAULT, ?, ?, CAST(? AS genero), ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, a.getNome());
			cmd.setInt(2, a.getMatricula());
			cmd.setString(3, a.getGenero().toString());
			cmd.setDate(4, a.getData_nascimento());

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				a.setId(key.getInt(1));
			}


		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM alunos WHERE id = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Aluno select(int id) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, matricula, t_genero, data_nasc FROM alunos WHERE id = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(id);
				aluno.setNome(rs.getString("nome"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setGenero(Genero.valueOf(rs.getString("t_genero")));
				aluno.setData_nascimento(rs.getDate("data_nasc"));
				return aluno;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Aluno select(String nome) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, matricula, t_genero, data_nasc FROM alunos WHERE nome = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setString(1, nome);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(nome);
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setGenero(Genero.valueOf(rs.getString("t_genero")));
				aluno.setData_nascimento(rs.getDate("data_nasc"));
				return aluno;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE alunos "
			   		   + "SET nome = ?, matricula = ?, t_genero = CAST(? as genero), data_nasc = ? WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, aluno.getNome());
			cmd.setInt(2, aluno.getMatricula());
			cmd.setString(3, aluno.getGenero().toString());
			cmd.setDate(4, aluno.getData_nascimento());
			cmd.setInt(5, aluno.getId());

			cmd.execute();
			
			sql = "DELETE FROM alunos WHERE id = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, aluno.getId());
			cmd.execute();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, matricula, t_genero, data_nasc FROM alunos ORDER BY id DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); 

			List<Aluno> alunos = new ArrayList();
			
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setGenero(Genero.valueOf(rs.getString("t_genero")));
				aluno.setData_nascimento(rs.getDate("data_nasc"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectPage(int pagina) {
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id, nome, matricula, t_genero, data_nasc FROM alunos ORDER BY nome DESC OFFSET ? LIMIT 2";
			

			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); 

			List<Aluno> alunos = new ArrayList();
			
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setGenero(Genero.valueOf(rs.getString("t_genero")));
				aluno.setData_nascimento(rs.getDate("data_nasc"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
