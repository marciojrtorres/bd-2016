package vinicius.dao;

import java.sql.*;

import java.util.*;

import vinicius.DB;
import vinicius.Status_ped;
import vinicius.model.*;

public class PedidoDAO {
private DB db = new DB();
	
	private static final int LIMIT = 2;
	/*
	 * Este met�do insere dados sobre os pedidos na tabela pedidos
	 * Insere o codigo do pedido, do item e sua quantidade na tabela detalhes_pedidos
	 */
	public void insert(Pedido pedido) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO pedidos VALUES(DEFAULT, ?, ?, ?, ?, ?, CAST(? AS status_ped));";
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cmd.setInt(1, pedido.getCod_vend());
			cmd.setInt(2, pedido.getCod_cli());
			cmd.setDate(3, new java.sql.Date(pedido.getData_pedido().getTime()));
			cmd.setDate(4, new java.sql.Date(pedido.getData_entrega().getTime()));
			cmd.setDouble(5, pedido.getFrete());
			cmd.setString(6, pedido.getStatus().toString());

			cmd.execute();

			ResultSet key = cmd.getGeneratedKeys();

			if (key.next()) {
				pedido.setCodigo(key.getInt(1));
			}
			for(Item p : pedido.getItens()){
				sql = "INSERT INTO detalhes_pedidos VALUES(?,?,?)";
				cmd = con.prepareStatement(sql);
				
				cmd.setInt(1, p.getCod_ped());
				cmd.setInt(2, p.getProduto().getCodigo());
				cmd.setInt(3, p.getQuantidade());
				
				cmd.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 */
	public boolean delete(Integer codigo) {
		try (Connection con = db.getConnection()) {
			
			String sql = "DELETE FROM detalhes_pedidos  WHERE cod_ped = ?;";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, codigo);
			cmd.execute();
			
			sql = "DELETE FROM pedidos WHERE codigo = ?;";
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
	public boolean delete(Pedido pedido){
		if(pedido.getCodigo() == null){
			throw new RuntimeException("ID NULO");
		}
		return delete(pedido.getCodigo());
	}
	/*
	 * Seleciona um vendedor a partir do seu codigo
	 */
	public Pedido select(Integer codigo) {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM pedidos WHERE codigo = ?";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setInt(1, codigo);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setCodigo(codigo);
				pedido.setCod_vend(rs.getInt("cod_vend"));
				pedido.setCod_cli(rs.getInt("cod_cli"));
				pedido.setData_pedido(rs.getDate("data_pedido"));
				pedido.setData_entrega(rs.getDate("data_entrega"));
				pedido.setFrete(rs.getDouble("frete"));
				pedido.setStatus(Status_ped.valueOf(rs.getString("status")));
				
				List<Item> itens = new ArrayList();
				sql = "SELECT * FROM detalhes_pedidos WHERE cod_ped = ?";
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, codigo);
				rs = cmd.executeQuery();
				while(rs.next()){
					Item item = new Item();
					item.setCod_ped(rs.getInt("cod_ped"));
					item.setProduto(ProdutoDAO.select(rs.getInt("cod_prod")));
					item.setQuantidade(rs.getInt("quantidade"));
					itens.add(item);
				}
				pedido.setItens(itens);
				
				return pedido;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 */
	public boolean update(Pedido pedido){
		try(Connection con = db.getConnection()){
			
		String sql = "UPDATE pedidos SET"
				+ " cod_vend = ?, cod_cli = ?, data_pedido = ?, data_entrega = ?, frete = ?, status = CAST(? AS status_ped)"
				+ " WHERE codigo = ?";
				
		PreparedStatement cmd = con.prepareStatement(sql);
		
		cmd.setInt(1, pedido.getCod_vend());
		cmd.setInt(2, pedido.getCod_cli());
		cmd.setDate(3, new java.sql.Date(pedido.getData_pedido().getTime()));
		cmd.setDate(4, new java.sql.Date(pedido.getData_entrega().getTime()));
		cmd.setDouble(5, pedido.getFrete());
		cmd.setString(6, pedido.getStatus().toString());
		cmd.setInt(7, pedido.getCodigo());
		
		cmd.execute();
		
		for(Item i : pedido.getItens()){
			sql = "UPDATE detalhes_pedidos SET"
					+ " cod_ped = ?, cod_prod = ?, quantidade = ?"
					+ " WHERE codigo = ?";
			cmd = con.prepareStatement(sql);
			
			cmd.setInt(1, i.getCod_ped());
			cmd.setInt(2, i.getProduto().getCodigo());
			cmd.setInt(3, i.getQuantidade());
			cmd.setInt(4, pedido.getCodigo());
			cmd.execute();
		}
		return true;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Pedido> selectAll() {
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM pedidos ORDER BY codigo DESC";

			PreparedStatement cmd = con.prepareStatement(sql);

			ResultSet rs = cmd.executeQuery(); // consulta devolve um result set

			List<Pedido> pedidos = new ArrayList();
			
			while(rs.next()) {

				Pedido pedido = new Pedido();
				
				pedido.setCodigo(rs.getInt("codigo"));
				pedido.setCod_vend(rs.getInt("cod_vend"));
				pedido.setCod_cli(rs.getInt("cod_cli"));
				pedido.setData_pedido(rs.getDate("data_pedido"));
				pedido.setData_entrega(rs.getDate("data_entrega"));
				pedido.setFrete(rs.getDouble("frete"));
				pedido.setStatus(Status_ped.valueOf(rs.getString("status")));
				
				List<Item> itens = new ArrayList();
				sql = "SELECT * FROM detalhes_pedidos WHERE cod_ped = ?";
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, pedido.getCodigo());
				ResultSet rs2 = cmd.executeQuery();
				while(rs2.next()){
					Item item = new Item();
					item.setCod_ped(rs2.getInt("cod_ped"));
					item.setProduto(ProdutoDAO.select(rs2.getInt("cod_prod")));
					item.setQuantidade(rs2.getInt("quantidade"));
					itens.add(item);
				}
				pedido.setItens(itens);
				pedidos.add(pedido);
			}
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 
	 * 
	 */
	public List<Pedido> selectPage(int pagina) {
		
		if(pagina < 1){
			return null;
		}
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM pedidos ORDER BY codigo DESC OFFSET ? LIMIT 2";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1) * LIMIT;
			
			cmd.setInt(1, offset);
			
			ResultSet rs = cmd.executeQuery();

			List<Pedido> pedidos = new ArrayList();
			
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigo"));
				pedido.setCod_vend(rs.getInt("cod_vend"));
				pedido.setCod_cli(rs.getInt("cod_cli"));
				pedido.setData_pedido(rs.getDate("data_pedido"));
				pedido.setData_entrega(rs.getDate("data_entrega"));
				pedido.setFrete(rs.getDouble("frete"));
				pedido.setStatus(Status_ped.valueOf(rs.getString("status")));
				
				List<Item> itens = new ArrayList();
				sql = "SELECT * FROM detalhes_pedidos WHERE cod_ped = ?";
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, pedido.getCodigo());
				ResultSet rs2 = cmd.executeQuery();
				while(rs2.next()){
					Item item = new Item();
					item.setCod_ped(rs2.getInt("cod_ped"));
					item.setProduto(ProdutoDAO.select(rs2.getInt("cod_prod")));
					item.setQuantidade(rs2.getInt("quantidade"));
					itens.add(item);
				}
				pedido.setItens(itens);
				pedidos.add(pedido);
			}
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Pedido> selectByYear(int ano){
		
		try (Connection con = db.getConnection()) {

			String sql = "SELECT * FROM pedidos GROUP BY codigo HAVING EXTRACT(YEAR FROM data_pedido) = ?";
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			cmd.setInt(1, ano);
			
			ResultSet rs = cmd.executeQuery();

			List<Pedido> pedidos = new ArrayList();
			
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setCodigo(rs.getInt("codigo"));
				pedido.setCod_vend(rs.getInt("cod_vend"));
				pedido.setCod_cli(rs.getInt("cod_cli"));
				pedido.setData_pedido(rs.getDate("data_pedido"));
				pedido.setData_entrega(rs.getDate("data_entrega"));
				pedido.setFrete(rs.getDouble("frete"));
				pedido.setStatus(Status_ped.valueOf(rs.getString("status")));
				
				List<Item> itens = new ArrayList();
				sql = "SELECT * FROM detalhes_pedidos WHERE cod_ped = ?";
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, pedido.getCodigo());
				ResultSet rs2 = cmd.executeQuery();
				while(rs2.next()){
					Item item = new Item();
					item.setCod_ped(rs2.getInt("cod_ped"));
					item.setProduto(ProdutoDAO.select(rs2.getInt("cod_prod")));
					item.setQuantidade(rs2.getInt("quantidade"));
					itens.add(item);
				}
				pedido.setItens(itens);
				pedidos.add(pedido);
			}
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
