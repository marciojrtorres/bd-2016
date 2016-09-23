package erick.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import erick.DB;
import erick.model.Clientes;
import erick.model.Enumerado;
import erick.model.Produtos;

public class ClientesDAO {
	
	private static final int LIMIT = 2;
	
	private DB db = new DB();
	
	public void insert (Clientes c){
		try (Connection con = db.getConnection()) {
			
			String sql = "INSERT INTO clientes (nome , sobrenome , nascimento) "
					+ "VALUES (?,?,?);";
			PreparedStatement cmd = 
					con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getSobrenome());
			cmd.setDate(3, c.getNascimento());
			cmd.execute();
			
			ResultSet key = cmd.getGeneratedKeys();
			
			if (key.next()) {
				c.setId(key.getInt(1));
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	public Clientes verCliente(Clientes c){
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return selectCliente(c.getId());
	}

	public Clientes selectCliente(int id) {
		try (Connection con = db.getConnection()) {
			
			String sql = "SELECT nome, sobrenome, nascimento FROM clientes WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
		
			ResultSet rs = cmd.executeQuery();
			
			if (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setId(id);
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setNascimento(rs.getDate("nascimento"));
				return cliente;
			}
			
		return null;
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Clientes> selectAllClientes() {
		try (Connection con = db.getConnection()) {
			
			String sql = "SELECT id , nome, sobrenome, nascimento FROM clientes ORDER BY id ASC";
			
			PreparedStatement cmd = con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery();
			
			List<Clientes> clientes = new ArrayList();
			
			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setNascimento(rs.getDate("nascimento"));
				clientes.add(cliente);
			}
			
		return clientes;
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(int id){
		try (Connection con = db.getConnection()) {
			
			String sql = "DELETE FROM clientes WHERE id = ? ";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			return !cmd.execute();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public boolean delete(Clientes c) {
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return delete(c.getId());
	}

	public void update(Clientes c) {
			if (c.getId() == null) {
				throw new RuntimeException("O id eh nulo, nao pode ser excluido");
			}
			try (Connection con = db.getConnection()) {
			
			String sql = "UPDATE clientes SET nome = ? ,"
					+ "sobrenome = ? ,"
					+ "nascimento = ? "
					+ "WHERE id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
				
			cmd.setString(1, c.getNome());
			cmd.setString(2, c.getSobrenome());
			cmd.setDate(3, c.getNascimento());
			cmd.setInt(4, c.getId());
			cmd.execute();
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	public List<Produtos> selectListaDeProdutos(int id) {

		try (Connection con = db.getConnection()) {
			
			String sql = "SELECT pr.nome , pr.preco, pr.raridade "
					+ "FROM clientes AS cli"
					+ "		INNER JOIN  produtos AS pr ON (pr.id = cli.id)"
					+ "			WHERE cli.id = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery();
			
			List<Produtos> produtos = new ArrayList();
			
			while (rs.next()) {
				Produtos produto = new Produtos();
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getInt("preco"));
				produto.setRaridade(Enumerado.valueOf(rs.getString("raridade")));
				produtos.add(produto);
			}
			
		return produtos;
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	

	public List<Produtos> verProdutosCliente(Clientes c){
		if (c.getId() == null) {
			throw new RuntimeException("O id eh nulo, nao pode ser excluido");
		}
		return selectListaDeProdutos(c.getId());
	}
	
	
	public List<Clientes> selectPage(int pagina) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT id, nome, sobrenome, nascimento "
					   + "FROM clientes "
					   + "ORDER BY nome, sobrenome DESC "
					   + "OFFSET ? "
					   + "LIMIT " + LIMIT;
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Clientes> clientes = new ArrayList();
			
			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setNascimento(rs.getDate("nascimento"));
				clientes.add(cliente);
			}
			
			return clientes;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

		
	
		
	}









