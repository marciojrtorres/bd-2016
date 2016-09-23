package beatriz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beatriz.DB;
import beatriz.model.Escritor;
import beatriz.model.Livro;

public class LivroDAO {
private static final int LIMIT = 2;
	
	private DB db = new DB();
	
	public void insert(Livro livro) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO livros "
					+ "(id_escritor, titulo, publicacao, livro_genero) VALUES "
					+ "(?, ?, ?, CAST(? AS genero));";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			cmd.setInt(1, livro.getId_escritor());
			cmd.setString(2, livro.getTitulo());
			cmd.setDate(3, livro.getPublicacao());
			cmd.setString(4, livro.getLivro_genero().toString());
			cmd.execute();
	// pegar a chave gerada
		/*	ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				livro.setId_escritor(key.getInt(1));
			}*/
			// fim pegar chave
			
		/*	// inserindo os telefones
			String sqlTelefone = 
					"INSERT INTO telefones VALUES (?,?,?)";
			for (Telefone t : c.getTelefones()) {
				PreparedStatement cmdTel = 
						con.prepareStatement(sqlTelefone);
				
				cmdTel.setInt(1, c.getId());
				cmdTel.setString(2, t.getDdd());
				cmdTel.setString(3, t.getNumero());
				
				cmdTel.execute();
			}
			//
			
			*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(String titulo) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM livros WHERE titulo = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setString(1, titulo);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	public List<Livro> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_escritor, titulo, publicacao, livro_genero"
					   + "FROM livros";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Livro> livros = new ArrayList();
			
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId_escritor(rs.getInt("id_escritor"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPublicacao(rs.getDate("publicacao"));
				livro.setLivro_genero(rs.getString("livro_genero"));
				livros.add(livro);
			}
			
			return livros;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Livro livro) {
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE livros "
			   		   + "SET titulo = ?, publicacao = ?, livro_genero = CAST(? AS genero) "
					   + "WHERE id_escritor = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, livro.getTitulo());
			cmd.setDate(2, livro.getPublicacao());
			cmd.setString(3, livro.getLivro_genero().toString());
			cmd.setInt(4, livro.getId_escritor());
			cmd.execute();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Livro select(String titulo) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_escritor, titulo, publicacao, livro_genero "
					   + "FROM livros WHERE titulo = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setString(1, titulo);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Livro livro = new Livro();
				livro.setId_escritor(rs.getInt("id_escritor"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPublicacao(rs.getDate("publicacao"));
				livro.setLivro_genero(rs.getString("livro_genero"));
				return livro;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
