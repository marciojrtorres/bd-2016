package eduarda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eduarda.*;
import eduarda.model.Aluno;
import eduarda.model.Curso;
import eduarda.model.Matricula;

public class MatriculaDAO {
	
	private static final int LIMIT = 2;
	
	private DB db = new DB();
	
	Date now = new Date(System.currentTimeMillis());
	
	public void insert(Matricula m) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO alunos_por_curso VALUES(?, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setInt(1, m.getId_aluno());
			cmd.setInt(2, m.getId_curso());
			cmd.setDate(3, m.getData_matricula());
			
			cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM alunos_por_curso WHERE id_aluno = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Matricula> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id_aluno, id_curso, data_matricula FROM alunos_por_curso ORDER BY id_aluno DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); 

			List<Matricula> matriculas = new ArrayList();
			
			while(rs.next()) {
				Matricula matricula = new Matricula();
				matricula.setId_aluno(rs.getInt("id_aluno"));
				matricula.setId_curso(rs.getInt("id_curso"));
				matricula.setData_matricula(rs.getDate("data_matricula"));
				matriculas.add(matricula);
			}
			return matriculas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Matricula select(int id) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id_aluno, id_curso, data_matricula FROM alunos_por_curso WHERE id_aluno = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery(); 
			if (rs.next()) {
				Matricula matricula = new Matricula();
				matricula.setId_aluno(rs.getInt("id_aluno"));
				matricula.setId_curso(rs.getInt("id_curso"));
				matricula.setData_matricula(rs.getDate("data_matricula"));
				return matricula;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Matricula> selectPage(int pagina) {
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT id_curso, id_aluno, data_matricula FROM alunos_por_curso ORDER BY id_aluno DESC OFFSET ? LIMIT 2";
			

			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); 

			List<Matricula> matriculas = new ArrayList();
			
			while(rs.next()) {
				Matricula matricula = new Matricula();
				matricula.setId_aluno(rs.getInt("id_aluno"));
				matricula.setId_curso(rs.getInt("id_curso"));
				matricula.setData_matricula(rs.getDate("data_matricula"));
				matriculas.add(matricula);
			}
			return matriculas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Matricula matricula) {
		if (matricula.getId_aluno() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE alunos_por_curso "
			   		   + "SET id_curso = ?, data_matricula = ? WHERE id_aluno = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setInt(1, matricula.getId_curso());
			cmd.setDate(2, matricula.getData_matricula());
			cmd.setInt(3, matricula.getId_aluno());
			

			cmd.execute();
			
			sql = "DELETE FROM alunos_por_curso WHERE id_aluno = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, matricula.getId_aluno());
			cmd.execute();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
