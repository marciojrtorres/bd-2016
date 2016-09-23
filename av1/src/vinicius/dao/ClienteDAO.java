package vinicius.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vinicius.*;
import vinicius.model.*;

public class ClienteDAO {
	
	private DB db = new DB();
	
	private static final int LIMIT = 2;
	
	/*
	 * Este met�do insere clientes no banco de dados e retonar a chave que foi atribuida a esta nova linha
	 */
	public void insert(Cliente cliente) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO clientes VALUES(DEFAULT, ?, ?, ?, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, cliente.getNome());
			cmd.setString(2, cliente.getSobrenome());
			cmd.setString(3, cliente.getCidade());
			cmd.setString(4, cliente.getUf());
			cmd.setString(5, cliente.getCep());

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				cliente.setCodigo(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este met�do exclui um registro do banco de dados a partir do seu codigo
	 * Exclui tanto o cliente quanto os seus pedidos feitos
	 */
	public boolean delete(Integer codigo) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM detalhes_pedidos d WHERE d.cod_ped IN (SELECT codigo FROM pedidos WHERE cod_cli = ?)";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM pedidos WHERE cod_cli = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM clientes WHERE codigo = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este metodo deleta um cliente a partir de seu objeto
	 */
	public boolean delete(Cliente cliente){
		if(cliente.getCodigo() == null){
			throw new RuntimeException("O id eh nulo");
		}
		return delete(cliente.getCodigo());
	}
	/*
	 * Este metodo seleciona o nome e o sobrenome de um cliente a partir do seu id
	 */
	public Cliente select(Integer codigo) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM clientes WHERE codigo = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, codigo);
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(codigo);
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setUf(rs.getString("uf"));
				return cliente;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este metodo faz um update em algum contato do banco 
	 */
	public boolean update(Cliente cliente){
		try(Connection con = db.getConnection()){
			
		String sql = "UPDATE clientes SET nome = ?, sobrenome = ?, cidade = ?, uf = ?, cep = ? WHERE codigo = ?";
				
		PreparedStatement cmd = con.prepareStatement(sql);
		
		cmd.setString(1, cliente.getNome());
		cmd.setString(2, cliente.getSobrenome());
		cmd.setString(3, cliente.getCidade());
		cmd.setString(4, cliente.getUf());
		cmd.setString(5, cliente.getCep());
		cmd.setInt(6, cliente.getCodigo());
		
		return cmd.execute();
		
		/*sql = "DELETE FROM pedidos WHERE codigo = ?";
		
		cmd = con.prepareStatement(sql);
		
		cmd.setInt(1, cliente.getCodigo());
		cmd.execute();*/

		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 */
	public List<Cliente> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM clientes ORDER BY codigo DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery();

			List<Cliente> clientes = new ArrayList();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setUf(rs.getString("uf"));
				cliente.setCep(rs.getString("cep"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Cliente> selectPage(int pagina) {
		
		if(pagina < 1){
			return null;
		}
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM clientes ORDER BY nome, sobrenome DESC OFFSET ? LIMIT 2";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Cliente> clientes = new ArrayList();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setUf(rs.getString("uf"));
				cliente.setCep(rs.getString("cep"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Cliente> selectByCidade(String cidade){
		
		cidade = cidade.toUpperCase();
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM clientes  WHERE UPPER(cidade) =? GROUP BY codigo";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, cidade);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Cliente> clientes = new ArrayList();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setUf(rs.getString("uf"));
				cliente.setCep(rs.getString("cep"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
