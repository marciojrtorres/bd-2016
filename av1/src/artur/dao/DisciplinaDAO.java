package artur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import artur.bd.DB;
import artur.model.Aluno;
import artur.model.Disciplina;


public class DisciplinaDAO implements IDAO<Disciplina> {
	private static final int limit = 10;
	private DB db = new DB();
	
	@Override
	public void insert(Disciplina t) {
		// TODO Auto-generated method stub
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO disciplinas (nome, ramo, id_professor) VALUES (?, ?, ?);";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, t.getNome());
			cmd.setString(2, t.getRamo());
			cmd.setInt(3, t.getProfessor().getId());
			cmd.execute();
			
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				t.setId(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM disciplinas WHERE id=?;";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setInt(1, id);
			cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Disciplina d) {
		// TODO Auto-generated method stub
		if (d.getId() == null) throw new RuntimeException("Contato com id nulo como este n�o pode ser exclu�do.");
		else delete(d.getId());
	}

	@Override
	public List<Disciplina> selectAll() {
		// TODO Auto-generated method stub
		try (Connection con = db.getConnection()) {
			String sql = "SELECT disciplina.nome, disciplina.ramo FROM disciplinas WHERE id = ? ORDER BY nome DESC";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = cmd.executeQuery();		//consulta
			List<Disciplina> lista = new ArrayList();
					
			if (rs.next()) {
				Disciplina d = new Disciplina();
				d.setNome(rs.getString("nome"));
				d.setRamo(rs.getString("ramo"));
			}
					
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Disciplina> selectPage(Integer page) {
		// TODO Auto-generated method stub
		try (Connection con = db.getConnection()) {
			String sql = "SELECT disciplina.nome, disciplina.ramo FROM disciplinas WHERE id = ? ORDER BY nome DESC OFFSET ? LIMIT" + limit;
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int offset = (page - 1) * limit;		//primeiro resultado na lista
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery();		//consulta
			List<Disciplina> lista = new ArrayList();
					
			if (rs.next()) {
				Disciplina d = new Disciplina();
				d.setNome(rs.getString("nome"));
				d.setRamo(rs.getString("ramo"));
			}
					
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Disciplina select(Integer id) {
		// TODO Auto-generated method stub
		try (Connection con = db.getConnection()) {
			String sql = "SELECT disciplina.nome, disciplina.ramo FROM disciplinas WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery();		//consulta
			
			if (rs.next()) {
				Disciplina d = new Disciplina();
				d.setNome(rs.getString("nome"));
				d.setRamo(rs.getString("ramo"));
				
				return d;
			}
			
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Disciplina t) {
		// TODO Auto-generated method stub
		if (t.getId() == null) throw new RuntimeException("Disciplina com id nulo como este n�o pode ser exclu�da.");
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE disciplinas SET nome = ?, ramo = ?, id_professor = ? WHERE id = ?;";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, t.getNome());
			cmd.setString(2, t.getRamo());
			cmd.setInt(3, t.getProfessor().getId());
			cmd.setInt(4, t.getId());
			
			cmd.execute();
			
			// atualizando os telefones
			sql = "DELETE FROM alunos WHERE id_disciplina = ?";
			cmd.getConnection().prepareStatement(sql);
			cmd.setInt(1, t.getId());
			cmd.execute();
			
			sql = "INSERT INTO alunos (nome, nascimento, sexo, endereco, telefone, email) VALUES (?, ?, ?, ?, ?, ?);";
			
			for (Aluno a : t.getAlunos()) {
				cmd = con.prepareStatement(sql);
				
				cmd.setString(1, a.getNome());
				cmd.setDate(2, new java.sql.Date(a.getNascimento().getTime()));
				cmd.setString(3, a.getSexo().toString());
				cmd.setString(4, a.getEndereco());
				cmd.setString(5, a.getTelefone());
				cmd.setString(6, a.getEmail());
				
				cmd.execute();
			}
			
			sql = "INSERT INTO alunos_disciplinas (id_aluno, id_disciplina) VALUES (?, ?);";
			
			for (Aluno a : t.getAlunos()) {
				cmd = con.prepareStatement(sql);
				
				cmd.setInt(1, a.getId());
				cmd.setInt(2, t.getId());
				
				cmd.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}