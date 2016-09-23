package vinicius.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vinicius.DB;
import vinicius.model.*;

public class ProdutoDAO {

	private static DB db = new DB();
	
	private static final int LIMIT = 5;
	/*
	 * Este metodo adiciona um produto no banco de dados
	 * 
	 */
	public void insert(Produto produto) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO produtos VALUES(DEFAULT, ?, ?, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, produto.getDescricao());
			cmd.setString(2, produto.getCategoria());
			cmd.setDouble(3, produto.getPreco());
			cmd.setInt(4, produto.getQtd_estoque());

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				produto.setCodigo(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Esta linha deleta um produto do estoque
	 * 
	 */
	public boolean delete(Integer codigo) {
		try (Connection con = db.getConnection()) {
			
			String sql = "DELETE FROM detalhes_pedidos d WHERE d.cod_ped IN (SELECT codigo FROM pedidos WHERE cod_prod = ?)";
			PreparedStatement  cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM pedidos p WHERE p.codigo IN (SELECT cod_ped from detalhes_pedidos where cod_prod = ?)";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM produtos WHERE codigo = ?";
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
	public boolean delete(Produto produto){
		if(produto.getCodigo() == null){
			throw new RuntimeException("O id eh nulo");
		}
		return delete(produto.getCodigo());
	}
	/*
	 * Este metodo selecina um produto a partir de seu codigo
	 */
	public static Produto select(Integer codigo) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM produtos WHERE codigo = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, codigo);
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			if (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(codigo);
				produto.setDescricao(rs.getString("descricao"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
				return produto;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public boolean update(Produto produto){
		try(Connection con = db.getConnection()){
			
		String sql = "UPDATE produtos SET descricao = ?, categoria = ?, preco = ?, qtd_estoque = ? WHERE codigo = ?";
				
		PreparedStatement cmd = con.prepareStatement(sql);
		
		cmd.setString(1, produto.getDescricao());
		cmd.setString(2, produto.getCategoria());
		cmd.setDouble(3, produto.getPreco());
		cmd.setInt(4, produto.getQtd_estoque());
		cmd.setInt(5, produto.getCodigo());
		
		return cmd.execute();
		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Produto> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM produtos ORDER BY codigo DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Produto> produtos = new ArrayList();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
				produtos.add(produto);
			}
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Produto> selectPage(int pagina) {
		
		if(pagina < 1){
			return null;
		}
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM produtos ORDER BY codigo OFFSET ? LIMIT 2";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Produto> produtos = new ArrayList();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
				produtos.add(produto);
			}
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Produto> selectByCategoria(String categoria) {
		
		categoria = categoria.toUpperCase();
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM produtos WHERE UPPER(categoria) = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
						
			cmd.setString(1, categoria);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Produto> produtos = new ArrayList();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
				produtos.add(produto);
			}
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}