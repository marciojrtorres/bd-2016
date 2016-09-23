package laurad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import laurad.DB;
import laurad.model.Jogo;

public class JogoDAO {
		
	private static final int LIMIT = 3;
	
	private DB db = new DB();

	public JogoDAO() {

	}

	public void insertJogo(Jogo jogo) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO jogos VALUES (DEFAULT, ?);";
			PreparedStatement cmd = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, jogo.getNome());
			cmd.execute();
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				jogo.setIdJogo(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Jogo selectJogo(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome_jogo FROM jogos WHERE id_jogo = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			if (rs.next()) {
				Jogo jogo = new Jogo();
				jogo.setNome(rs.getString("nome_jogo"));
				jogo.setIdJogo(id);
				return jogo;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;

	}

	public List<Jogo> selectJogos() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome_jogo, id_jogo FROM jogos ORDER BY id_jogo";
			PreparedStatement cmd = con.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			List<Jogo> jogos = new ArrayList<Jogo>();
			while (rs.next()) {
				Jogo jogo = new Jogo();
				jogo.setNome(rs.getString("nome_jogo"));
				jogo.setIdJogo(rs.getInt("id_jogo"));
				jogos.add(jogo);
			}
			return jogos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	public boolean deleteJogo(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM jogos WHERE id_jogo = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deleteJogo(Jogo jogo) {
		if (jogo.getIdJogo() == null) {
			throw new RuntimeException("Impossivel apagar um usuario nulo");
		}
		return deleteJogo(jogo.getIdJogo());
	}

	public void updateJogo(Jogo jogo) {
		if (jogo.getIdJogo() == null) {
			throw new RuntimeException("Impossivel apagar um usuario nulo");
		}
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE jogos SET nome_jogo = ? WHERE id_jogo = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, jogo.getNome());
			cmd.setInt(2, jogo.getIdJogo());
			cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Jogo> selectJogoByNome(String nomeJogo) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id_jogo FROM jogos WHERE nome_jogo = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, nomeJogo);
			ResultSet rs = cmd.executeQuery();
			List<Jogo> jogos = new ArrayList<Jogo>();
			while (rs.next()) {
				Jogo jogo = new Jogo();
				jogo.setIdJogo(rs.getInt("id_jogo"));
				jogo.setNome(nomeJogo);
				jogos.add(jogo);
			}
			return jogos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Jogo> selectJogosPaginado(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome_jogo, id_jogo FROM jogos LIMIT " + getLimit() + " OFFSET ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			int offset = (pagina - 1) * getLimit(); 
			cmd.setInt(1, offset);	
			ResultSet rs = cmd.executeQuery();
			List<Jogo> jogos = new ArrayList<Jogo>();
			while (rs.next()) {
				Jogo jogo = new Jogo();
				jogo.setNome(rs.getString("nome_jogo"));
				jogo.setIdJogo(rs.getInt("id_jogo"));
				jogos.add(jogo);
			}
			return jogos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static int getLimit() {
		return LIMIT;
	}

}
