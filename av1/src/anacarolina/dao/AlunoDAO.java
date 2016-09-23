package anacarolina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import anacarolina.Database;
import anacarolina.model.Aluno;
import anacarolina.model.Curso;

public class AlunoDAO {
	
private static final int LIMIT = 2;
private Database db = new Database();
	
	public void insert(Aluno a){
		
		
		try (Connection con = db.getConnection() ) {
			String sql = "INSERT INTO alunos"
					+ "(matricula, nome, sobrenome, data_inicio, id_curso) VALUES"
					+ " (?, ?, ?, ?, ?);";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, a.getMatricula());
			cmd.setString(2, a.getNome());
			cmd.setString(3, a.getSobrenome());
			cmd.setDate(4, a.getData_inicio());
			cmd.setInt(5, a.getId_curso());
			cmd.executeUpdate();
			
			ResultSet key = cmd.getGeneratedKeys();
			if(key.next()){
				a.setId_aluno(key.getInt(1));
			}
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
		
	}
	

	public void delete(int id_aluno){
		
		try (Connection con = db.getConnection() ) {
			String sql = "DELETE FROM alunos WHERE id_aluno = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id_aluno);
			cmd.execute();
			
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}
	
	public Aluno select(int id_aluno) {
		
		try (Connection con = db.getConnection() ) {
			String sql = "SELECT matricula, nome, sobrenome, data_inicio, id_curso "
					+ "FROM alunos WHERE id_aluno = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id_aluno);
			
			
			ResultSet rs = cmd.executeQuery();
			
			if(rs.next()){
				
				Aluno aluno = new Aluno();
				aluno.setId_aluno(id_aluno);
				aluno.setMatricula(rs.getString("matricula"));	
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setData_inicio(rs.getDate("data_inicio"));
				aluno.setId_curso(rs.getInt("id_curso"));
				return aluno;
			}
			
			return null;
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}

	public void update(Aluno aluno){
		
		if (aluno.getId_aluno() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE alunos "
			   		   + "SET matricula = ?, nome = ?, sobrenome = ?, data_inicio = ?, id_curso = ? "
					   + "WHERE id_aluno = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			
			
			cmd.setString(1, aluno.getMatricula());
			cmd.setString(2, aluno.getNome());
			cmd.setString(3, aluno.getSobrenome());
			cmd.setDate(4, aluno.getData_inicio());
			cmd.setInt(5, aluno.getId_curso());
			cmd.setInt(6, aluno.getId_aluno());
			cmd.execute();
			
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectPage(int pagina) {
		
		try (Connection con = db.getConnection() ) {
			String sql = "SELECT id_aluno, matricula, nome, sobrenome, data_inicio, id_curso "
					+ "FROM alunos "
					+ "ORDER BY nome DESC "
					+ "OFFSET ? "
					+ "LIMIT " + LIMIT;
		
			PreparedStatement cmd = 
				con.prepareStatement(sql);
		// 1 - 1 = 0 * 2 = 0
		// 2 - 1 = 1 * 2 = 2
		// 3 - 1 = 2 * 2 = 4
		int offset = (pagina - 1) * LIMIT;
		
		cmd.setInt(1, offset);
		
		ResultSet rs = cmd.executeQuery(); 
		
		List<Aluno> alunos = new ArrayList();
		
			while(rs.next()){
				
				Aluno aluno = new Aluno();
				aluno.setId_aluno(rs.getInt("id_aluno"));
				aluno.setMatricula(rs.getString("matricula"));	
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setData_inicio(rs.getDate("data_inicio"));
				aluno.setId_curso(rs.getInt("id_curso"));
				alunos.add(aluno);
			}
				return alunos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectAll(){
		try (Connection con = db.getConnection() ) {
			
			String sql = "SELECT id_aluno, matricula, nome, sobrenome, data_inicio, id_curso "
					+ "FROM alunos "
					+ "ORDER BY nome DESC ";
		
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			ResultSet rs = cmd.executeQuery(); 
			
			List<Aluno> alunos = new ArrayList();
			
			while(rs.next()){
				
				Aluno aluno = new Aluno();
				aluno.setId_aluno(rs.getInt("id_aluno"));
				aluno.setMatricula(rs.getString("matricula"));	
				aluno.setNome(rs.getString("nome"));
				aluno.setSobrenome(rs.getString("sobrenome"));
				aluno.setData_inicio(rs.getDate("data_inicio"));
				aluno.setId_curso(rs.getInt("id_curso"));
				alunos.add(aluno);
			}
			return alunos;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aluno> selectById_curso(int id_curso){
		
		try (Connection con = db.getConnection() ) {
			
			String sql = "SELECT id_aluno, matricula, nome, sobrenome, data_inicio, id_curso "
					+ "FROM alunos "
					+ "WHERE id_curso = ? "
					+ "ORDER BY nome DESC ";
		
		
		PreparedStatement cmd = 
				con.prepareStatement(sql);
		
		cmd.setInt(1, id_curso);
		
		ResultSet rs = cmd.executeQuery(); 
		
		List<Aluno> alunos = new ArrayList();
		
		while(rs.next()){
			
			Aluno aluno = new Aluno();
			aluno.setId_aluno(rs.getInt("id_aluno"));
			aluno.setMatricula(rs.getString("matricula"));	
			aluno.setNome(rs.getString("nome"));
			aluno.setSobrenome(rs.getString("sobrenome"));
			aluno.setData_inicio(rs.getDate("data_inicio"));
			aluno.setId_curso(rs.getInt("id_curso"));
			alunos.add(aluno);
		}
		
		return alunos;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}