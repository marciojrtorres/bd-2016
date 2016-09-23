package laurag.dao;

import java.sql.*;
import java.util.*;

import laurag.DB;
import laurag.model.*;

public class EscolaDAO {

	private static final int LIMIT = 5;
	private DB db = new DB();

	// INSERT
	public void insert(Aluno a) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO alunos (nome, sobrenome, sexo) VALUES (?, ?, CAST(? AS sexo));";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, a.getNome());
			cmd.setString(2, a.getSobrenome());
			cmd.setString(3, a.getSexo());
			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				a.setId(key.getInt(1));
			}

			String sqlMatricula = "INSERT INTO matriculas (id_aluno, id_disciplina, inscricao) VALUES (?, ?, ?)";
			for (Matricula m : a.getMatriculas()) {
				PreparedStatement cmdMatricula = con.prepareStatement(sqlMatricula);
				cmdMatricula.setInt(1, m.getId_aluno());
				cmdMatricula.setInt(2, m.getId_disciplina());
				cmdMatricula.setDate(3, m.getInscricao());
				cmdMatricula.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void insertDisciplina(Disciplina d) {
		try (Connection con = db.getConnection()) {
			String sqlDisciplina = "INSERT INTO disciplinas (nome, c_hora) VALUES (?, ?)";
			PreparedStatement cmd = con.prepareStatement(sqlDisciplina, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, d.getNome());
			cmd.setInt(2, d.getC_hora());
			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				d.setId(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void insertMatricula(Matricula m) {
		try (Connection con = db.getConnection()) {
			String sqlMatricula = "INSERT INTO matriculas (id_alunos, id_disciplinas, inscricao) VALUES (?, ?, ?)";
			PreparedStatement cmd = con.prepareStatement(sqlMatricula);
			cmd.setInt(1, m.getId_aluno());
			cmd.setInt(2, m.getId_disciplina());
			cmd.setDate(3, m.getInscricao());
			cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// DELETE
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

	public boolean delete(Aluno a) {
		if (a.getId() == null) {
			throw new RuntimeException("O id � nulo. N�o pode ser exclu�do");
		}
		return delete(a.getId());
	}

	// SELECT
	public Aluno select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT * FROM alunos WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(id);
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setSexo(rs.getString("sexo"));
				return aluno;
			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Aluno> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT * FROM alunos ORDER BY id";
			PreparedStatement cmd = con.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();

			List<Aluno> alunos = new ArrayList();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setSexo(rs.getString("sexo"));
				alunos.add(aluno);
			}
			return alunos;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Matricula> selectMatriculas(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id  � nulo. N�o pode carregar as matriculas.");
		}
		try (Connection con = db.getConnection()) {
			String sql = "SELECT * FROM matriculas WHERE id_alunos = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, aluno.getId());
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Matricula matricula = new Matricula();
				matricula.setId_aluno(rs.getInt("id_alunos"));
				matricula.setId_disciplina(rs.getInt("id_disciplinas"));
				aluno.addMatricula(matricula);
				List<Matricula> matriculas = aluno.getMatriculas();
				return matriculas;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Aluno> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, sobrenome, sexo FROM alunos ORDER BY nome, sobrenome DESC OFFSET ? LIMIT "
					+ LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);
			int offset = (pagina - 1) * LIMIT;
			cmd.setInt(1, offset);
			ResultSet rs = cmd.executeQuery();

			List<Aluno> alunos = new ArrayList();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setSexo(rs.getString("sexo"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// UPDATE
	public void update(Aluno aluno) {
		if (aluno.getId() == null) {
			throw new RuntimeException("O id � nulo, n�o pode ser atualizado");
		}

		try (Connection con = db.getConnection()) {
			String sql = "UPDATE alunos SET nome = ?, sobrenome = ? WHERE id = ?";

			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, aluno.getNome());
			cmd.setString(2, aluno.getSobrenome());
			cmd.setInt(3, aluno.getId());
			cmd.execute();

			sql = "DELETE FROM matriculas WHERE id_alunos = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, aluno.getId());
			cmd.execute();

			sql = "INSERT INTO matriculas (id_alunos, id_disciplinas, inscricao) VALUES (?, ?, ?)";
			for (Matricula m : aluno.getMatriculas()) {
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, aluno.getId());
				cmd.setInt(2, m.getId_aluno());
				cmd.setInt(3, m.getId_disciplina());
				cmd.setDate(4, m.getInscricao());
				cmd.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}