package joaovitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import joaovitor.locadora.DB;
import joaovitor.model.Cliente;
import joaovitor.model.PagDia;

public class ClienteDAO {

	private DB db = new DB();
	private static final int LIMIT = 2;

	public void insert(Cliente c) {

		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO clientes(nome, sobrenome, email,data_nasc,pag_emdia) VALUES(?,?,?,?,?);";
			PreparedStatement cmd = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getSobrenome());
			cmd.setString(3, c.getEmail());
			cmd.setDate(4, c.getData_nasc());
			if (c.getPagdia() == PagDia.sim) {
				cmd.setInt(5, 0);
			} else {
				cmd.setInt(5, 1);
			}

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				c.setId(key.getInt(1));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM clientes WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);

			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public boolean delete(Cliente c) {
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return delete(c.getId());
	}

	public Cliente select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nome,sobrenome,email,data_nasc,pag_emdia FROM clientes WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);

			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Cliente c = new Cliente();
				c.setId(id);
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("Sobrenome"));
				c.setEmail(rs.getString("email"));
				c.setData_nasc(rs.getDate("data_nasc"));
				if (rs.getInt("pag_emdia") == 0) {
					c.setPagdia(PagDia.sim);
				} else {
					c.setPagdia(PagDia.nao);
				}
				return c;

			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> selectAll() {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id,nome,sobrenome,email,data_nasc,pag_emdia FROM clientes ORDER BY id DESC";
			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery();

			List<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("Sobrenome"));
				c.setEmail(rs.getString("email"));
				c.setData_nasc(rs.getDate("data_nasc"));
				if (rs.getInt("pag_emdia") == 0) {
					c.setPagdia(PagDia.sim);
				} else {
					c.setPagdia(PagDia.nao);
				}

				clientes.add(c);

			}
			return clientes;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> selectPage(int pagina) {

		try (Connection con = db.getConnection()) {
			String sql = "SELECT id,nome,sobrenome,email,data_nasc,pag_emdia "
					+ " FROM clientes "
					+ " ORDER BY nome, sobrenome,email,data_nasc,pag_emdia DESC "
					+ "OFFSET ? " + "LIMIT " + LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);

			int offset = (pagina - 1) * LIMIT;

			cmd.setInt(1, offset);
			ResultSet rs = cmd.executeQuery();

			List<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("Sobrenome"));
				clientes.add(c);
				c.setEmail(rs.getString("email"));
				c.setData_nasc(rs.getDate("data_nasc"));
				if (rs.getInt("pag_emdia") == 0) {
					c.setPagdia(PagDia.sim);
				} else {
					c.setPagdia(PagDia.nao);
				}

			}
			return clientes;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Cliente c) {
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}

		try (Connection con = db.getConnection()) {
			String sql = "UPDATE clientes SET nome = ?, sobrenome = ?,email =?,data_nasc =?,pag_emdia=? WHERE id=?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getSobrenome());
			cmd.setString(3, c.getEmail());
			cmd.setDate(4, c.getData_nasc());
			if (c.getPagdia() == PagDia.sim) {
				cmd.setInt(5, 0);
			} else {
				cmd.setInt(5, 1);
			}
			cmd.setInt(6, c.getId());
			cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
