package erick.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import erick.DB;
import erick.model.*;

	public class ProdutosDAO {

		private DB db = new DB();
	
		public void insert (Produtos p){
			try (Connection con = db.getConnection()) {
				
				String sql = "INSERT INTO produtos (id , nome , preco, raridade) "
						+ "VALUES ( ? , ? , ? , enumeracao (?) )";
				PreparedStatement cmd = 
						con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				cmd.setInt(1, p.getId());
				cmd.setString(2, p.getNome());
				cmd.setInt(3, p.getPreco());
				cmd.setString(4 , p.getRaridade().toString());
				cmd.execute();
				
				ResultSet key = cmd.getGeneratedKeys();
				
				if (key.next()) {
					p.setId_produto(key.getInt(1));
				}
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}		
		}
		
		
		public Produtos verProdutos(Produtos p){
			if (p.getId_produto() == null) {
				throw new RuntimeException("O id eh nulo, nao pode ser excluido");
			}
			return selectProdutos(p.getId_produto());
		}
		
		public Produtos selectProdutos(int id) {
			try (Connection con = db.getConnection()) {
				
				String sql = "SELECT id, nome, preco, raridade FROM produtos  WHERE id_produto=?";
				
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1, id);
			
				ResultSet rs = cmd.executeQuery();
				
				if (rs.next()) {
					Produtos produto = new Produtos();
					produto.setId_produto(id);
					produto.setId(rs.getInt("id"));
					produto.setNome(rs.getString("nome"));
					produto.setPreco(rs.getInt("preco"));
					produto.setRaridade(Enumerado.valueOf(rs.getString("raridade")));
					return produto;
				}
				
			return null;
			
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public List<Produtos> selectAllProdutos() {
			try (Connection con = db.getConnection()) {
				
				String sql = "SELECT id_produto , id , nome, preco, raridade FROM produtos";
				
				PreparedStatement cmd = con.prepareStatement(sql);
			
				ResultSet rs = cmd.executeQuery();
				List<Produtos> produtos = new ArrayList();
				
				while (rs.next()) {
					Produtos produto = new Produtos();
					produto.setId_produto(rs.getInt("id_produto"));
					produto.setId(rs.getInt("id"));
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
		
		public boolean delete(int id){
			try (Connection con = db.getConnection()) {
				
				String sql = "DELETE FROM produtos WHERE id_produto = ? ";
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1, id);
				return !cmd.execute();
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
		}
		
		public boolean delete(Produtos p) {
			if (p.getId_produto() == null) {
				throw new RuntimeException("O id eh nulo, nao pode ser excluido");
			}
			return delete(p.getId_produto());
		}


		public void update(Produtos p) {
			if (p.getId_produto() == null) {
				throw new RuntimeException("O id eh nulo, nao pode ser excluido");
			}
			try (Connection con = db.getConnection()) {
			
			String sql = "UPDATE produtos SET nome = ? ,"
					+ "preco = ? ,"
					+ "raridade = enumeracao (?) "
					+ "WHERE id_produto = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
				
			cmd.setString(1, p.getNome());
			cmd.setInt(2, p.getPreco());
			cmd.setString(3, p.getRaridade().toString());
			cmd.setInt(4, p.getId_produto());
			cmd.execute();
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}


		public List<Produtos> selectByRaridade(Enumerado a) {
				try (Connection con = db.getConnection()) {
				
				String sql = "SELECT nome, preco, raridade"
						+ " FROM produtos WHERE raridade = Enumeracao( ? )";
				
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setString(1, a.toString());
				cmd.execute();
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



		
	
	
	
	
}
