package anacarolina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import anacarolina.Database;
import anacarolina.model.Curso;
import anacarolina.model.Curso.Modalidades;

public class CursoDAO {
	
	private Database db = new Database();
	private static final int LIMIT = 2;
	
	public void insert(Curso c){
		
		try (Connection con = db.getConnection() ) {
			String sql = "INSERT INTO cursos"
					+ "(nome, coordenador, modalidade) VALUES"
					+ " (?, ?, CAST(? AS modalidades));";
			
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getCoordenador());
			cmd.setString(3, c.getModalidade().toString());
			cmd.execute();
			
			
			ResultSet key = cmd.getGeneratedKeys();
			if(key.next()){
				c.setId_curso(key.getInt(1));
			}
			
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
		
	}
	
	public void delete(int id_curso){
		
		try (Connection con = db.getConnection() ) {
			String sql = "DELETE FROM cursos WHERE id_curso = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id_curso);
			cmd.execute();
			
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}

	public Curso select(int id_curso) {
		
		try (Connection con = db.getConnection() ) {
			String sql = "SELECT nome , coordenador, modalidade "
					+ "FROM cursos WHERE id_curso = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id_curso);
			
			
			ResultSet rs = cmd.executeQuery();
			
			
			if(rs.next()){
				String stringmodalidade = rs.getString("modalidade");
				Curso curso = new Curso();
				curso.setId_curso(id_curso);
				curso.setNome(rs.getString("nome"));
				curso.setCoordenador(rs.getString("coordenador"));
				curso.setModalidade(curso.StringEnum(stringmodalidade));
				return curso;
			}
			
			return null;
			
			
		} catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}
	
	public void update(Curso curso){
		
		if (curso.getId_curso() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE cursos "
			   		   + "SET nome = ?, coordenador = ?, modalidade = CAST(? AS modalidades) "
					   + "WHERE id_curso = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, curso.getNome());
			cmd.setString(2, curso.getCoordenador());
			cmd.setString(3, curso.getModalidade().toString());
			cmd.setInt(4, curso.getId_curso());
			cmd.execute();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Integer> selectId() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_curso "
					   + "FROM cursos ORDER BY id_curso DESC";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); 
			
			List<Integer> ids = new ArrayList();
			
			while (rs.next()) {
				Curso curso = new Curso();
				Integer id;
				
				id = (rs.getInt("id_curso"));
				ids.add(id);
			}
			
			return ids;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_curso, nome, coordenador, modalidade "
					   + "FROM cursos "
					   + "ORDER BY nome, coordenador DESC "
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
			
			List<Curso> cursos = new ArrayList();
			
			while (rs.next()) {
				Curso curso = new Curso();
				String stringmodalidade = rs.getString("modalidade");
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				curso.setCoordenador(rs.getString("coordenador"));
				curso.setModalidade(curso.StringEnum(stringmodalidade));
				cursos.add(curso);
			}
			
			return cursos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_curso, nome, coordenador, modalidade "
					   + "FROM cursos ORDER BY id_curso DESC";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Curso> cursos = new ArrayList();
			
			while (rs.next()) {
				Curso curso = new Curso();
				String stringmodalidade = rs.getString("modalidade");
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				curso.setCoordenador(rs.getString("coordenador"));
				curso.setModalidade(curso.StringEnum(stringmodalidade));
				cursos.add(curso);
			}
			
			return cursos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> selectByModalidade(Enum modalidade){
		
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_curso, nome, coordenador, modalidade "
					   + "FROM cursos "
					   + "WHERE modalidade = CAST(? AS modalidades) "
					   + "ORDER BY id_curso DESC";
			
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			
			cmd.setString(1, modalidade.toString());
		
			ResultSet rs = cmd.executeQuery(); 
			
			List<Curso> cursos = new ArrayList();
			
			while (rs.next()) {
				Curso curso = new Curso();
				String stringmodalidade = rs.getString("modalidade");
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setNome(rs.getString("nome"));
				curso.setCoordenador(rs.getString("coordenador"));
				curso.setModalidade(curso.StringEnum(stringmodalidade));
				cursos.add(curso);
			}
			
			return cursos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
