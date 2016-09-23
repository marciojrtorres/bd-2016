package laurad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import laurad.DB;
import laurad.model.Genero;
import laurad.model.Jogo;
import laurad.model.Usuario;

public class UsuarioDAO {
	
	private static final Integer LIMIT = 3;
	
	private DB db = new DB();

	public void insertUsuario(Usuario usuario) {
		try (Connection con = db.getConnection()) {
			// INSERT INTO usuarios VALUES (DEFAULT, nome, sobrenome, data,
			// genero);
			String sql = "INSERT INTO usuarios VALUES (DEFAULT, ?, ?, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, usuario.getNome());
			cmd.setString(2, usuario.getSobrenome());
			cmd.setDate(3, usuario.getDataDeNascimento());
			cmd.setInt(4, Genero.toInteger(usuario.getGenero()));
			cmd.execute();
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				usuario.setIdUsuario(key.getInt(1));
			}
			sql = "INSERT INTO usuarios_jogos VALUES(?, ?);";
			for (Jogo jogo : usuario.getJogos()) {
				PreparedStatement cmdTel = con.prepareStatement(sql);
				cmdTel.setInt(1, usuario.getIdUsuario());
				cmdTel.setInt(2, jogo.getIdJogo());
				cmdTel.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario selectUsuario(Integer id) {
		try (Connection con = db.getConnection()) {
			if (id != null) {
				String sql = "SELECT u.nome, u.sobrenome, u.data_de_nascimento, u.genero, j.nome_jogo, uj.id_jogof FROM usuarios u "
						+ "INNER JOIN usuarios_jogos uj ON (u.id_usuario = uj.id_usuariof) INNER JOIN "
						+ "jogos j ON (uj.id_jogof = j.id_jogo) WHERE id_usuario = ?";
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1, id);
				ResultSet rs = cmd.executeQuery();
				Usuario usuario = new Usuario();
				if (rs.next()) {
					usuario.setIdUsuario(id);
					usuario.setNome(rs.getString("nome"));
					usuario.setSobrenome(rs.getString("sobrenome"));
					usuario.setDataDeNascimento(rs.getDate("data_de_nascimento"));
					usuario.setGenero(Genero.fromInteger(rs.getInt("genero")));
					Jogo jogo = new Jogo();
					jogo.setNome(rs.getString("nome_jogo"));
					jogo.setIdJogo(rs.getInt("id_jogof"));
					usuario.addJogo(jogo);
				}
				while (rs.next()) {
					Jogo jogo = new Jogo();
					jogo.setNome(rs.getString("nome_jogo"));
					jogo.setIdJogo(rs.getInt("id_jogof"));
					usuario.addJogo(jogo);
				}
				return usuario;
			}
			throw new RuntimeException("ID nulo");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> selectUsuarios() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_usuario FROM usuarios ORDER BY id_usuario";
			ArrayList<Integer> codigos = new ArrayList<Integer>();
			PreparedStatement cmd = con.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				codigos.add(rs.getInt("id_usuario"));
			}
			List<Usuario> usuarios = new ArrayList<Usuario>();
			for (int i = 0; i < codigos.size(); i++) {
				usuarios.add(selectUsuario(codigos.get(i)));
			}
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> selectPageUsuarios(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT u.id_usuario FROM usuarios AS u LIMIT " + LIMIT + " OFFSET ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			int offset = (pagina - 1) * LIMIT;
			cmd.setInt(1, offset);
			ResultSet rs = cmd.executeQuery();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = selectUsuario(rs.getInt("id_usuario"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> selectUsuarioByGenero(Genero genero) {
		try (Connection con = db.getConnection()) {
			int g = Genero.toInteger(genero);
			String sql = "SELECT u.id_usuario FROM usuarios u WHERE u.genero = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, g);
			ResultSet rs = cmd.executeQuery();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = selectUsuario(rs.getInt("id_usuario"));
				usuarios.add(usuario);
			}
			return usuarios;			
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean deleteUsuario(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deleteUsuario(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			throw new RuntimeException("Impossivel apagar um usuario nulo");
		}
		return deleteUsuario(usuario.getIdUsuario());
	}
	
	public void updateUsuario(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			throw new RuntimeException("Impossivel apagar um usuario nulo");
		}
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE usuarios SET nome = ?, sobrenome = ?, genero = ?, data_de_nascimento = ? WHERE id_usuario = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, usuario.getNome());
			cmd.setString(2, usuario.getSobrenome());
			cmd.setInt(3, Genero.toInteger(usuario.getGenero()));
			cmd.setDate(4, usuario.getDataDeNascimento());
			cmd.setInt(5, usuario.getIdUsuario());
			cmd.execute();

			sql = "DELETE FROM usuarios_jogos WHERE id_usuariof = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, usuario.getIdUsuario());
			cmd.execute();
			
			sql = "INSERT INTO usuarios_jogos VALUES(?, ?)";
			cmd = con.prepareStatement(sql);
			for (Jogo jogo : usuario.getJogos()) {
				cmd.setInt(1, usuario.getIdUsuario());
				cmd.setInt(2, jogo.getIdJogo());
				cmd.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static Integer getLimit() {
		return LIMIT;
	}
}
