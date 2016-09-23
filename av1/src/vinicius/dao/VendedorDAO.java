package vinicius.dao;

import java.sql.*;
import java.util.*;

import vinicius.*;
import vinicius.model.*;

public class VendedorDAO {
	
	private DB db = new DB();
	
	private static final int LIMIT = 2;
	/*
	 * Este met�do insere vendedores no banco de dados e retonar a chave que foi atribuida a esta nova linha
	 */
	public void insert(Vendedor vendedor) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO vendedores VALUES(DEFAULT, ?, ?, ?, ?);";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setString(1, vendedor.getNome());
			cmd.setString(2, vendedor.getSobrenome());
			cmd.setDate(3, new java.sql.Date(vendedor.getData_nasc().getTime()));
			cmd.setString(4, vendedor.getRegiao());

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				vendedor.setCodigo(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este met�do exclui um registro do banco de dados a partir do seu codigo
	 */
	public boolean delete(Integer codigo) {
		try (Connection con = db.getConnection()) {
			
			String sql = " DELETE FROM pedidos WHERE cod_vend = ?;";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM vendedores WHERE codigo = ?;";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			return cmd.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este met�do exclui um registro do banco de dados a partir do seu objeto
	 */
	public boolean delete(Vendedor vendedor){
		if(vendedor.getCodigo() == null){
			throw new RuntimeException("ID NULO");
		}
		return delete(vendedor.getCodigo());
	}
	/*
	 * Seleciona um vendedor a partir do seu codigo
	 */
	public Vendedor select(Integer codigo) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM vendedores WHERE codigo = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, codigo);
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			if (rs.next()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setCodigo(codigo);
				vendedor.setNome(rs.getString("nome"));
				vendedor.setSobrenome(rs.getString("sobrenome"));
				vendedor.setData_nasc(rs.getDate("data_nasc"));
				vendedor.setRegiao(rs.getString("regiao"));
				
				return vendedor;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * Este metodo faz um update em algum contato do banco 
	 */
	public boolean update(Vendedor vendedor){
		try(Connection con = db.getConnection()){
			
		String sql = "UPDATE vendedores SET nome = ?, sobrenome = ?, data_nasc = ?, regiao = ? WHERE codigo = ?";
				
		PreparedStatement cmd = con.prepareStatement(sql);
		
		cmd.setString(1, vendedor.getNome());
		cmd.setString(2, vendedor.getSobrenome());
		cmd.setDate(3, new java.sql.Date(vendedor.getData_nasc().getTime()));
		cmd.setString(4, vendedor.getRegiao());
		cmd.setInt(6, vendedor.getCodigo());
		
		return cmd.execute();

		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Vendedor> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM vendedores ORDER BY codigo DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Vendedor> vendedores = new ArrayList();
			
			while(rs.next()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setCodigo(rs.getInt("codigo"));
				vendedor.setNome(rs.getString("nome"));
				vendedor.setSobrenome(rs.getString("sobrenome"));
				vendedor.setData_nasc(rs.getDate("data_nasc"));
				vendedor.setRegiao(rs.getString("regiao"));
				vendedores.add(vendedor);
			}
			return vendedores;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Vendedor> selectPage(int pagina) {
		
		if(pagina < 1){
			return null;
		}
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM vendedores ORDER BY nome, sobrenome DESC OFFSET ? LIMIT 2";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Vendedor> vendedores = new ArrayList();
			
			while(rs.next()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setCodigo(rs.getInt("codigo"));
				vendedor.setNome(rs.getString("nome"));
				vendedor.setSobrenome(rs.getString("sobrenome"));
				vendedor.setData_nasc(rs.getDate("data_nasc"));
				vendedor.setRegiao(rs.getString("regiao"));
				vendedores.add(vendedor);
			}
			return vendedores;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Vendedor> selectByRegiao(String regiao){
		
		regiao = regiao.toUpperCase();
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM vendedores WHERE UPPER(regiao) = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setString(1, regiao);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Vendedor> vendedores = new ArrayList();
			
			while(rs.next()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setCodigo(rs.getInt("codigo"));
				vendedor.setNome(rs.getString("nome"));
				vendedor.setSobrenome(rs.getString("sobrenome"));
				vendedor.setData_nasc(rs.getDate("data_nasc"));
				vendedor.setRegiao(rs.getString("regiao"));
				vendedores.add(vendedor);
			}
			return vendedores;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
