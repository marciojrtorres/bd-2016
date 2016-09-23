package anaflavia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import anaflavia.compras.Ligacao;
import anaflavia.model.Clientes;
import anaflavia.model.Compras;
import anaflavia.model.Tipo_compra;

public class ComprasDAO {
	//CREATE  READ  UPDATE DELETE - CRUD
		private static final int LIMIT = 2;
		private Ligacao lig = new Ligacao();
		
		public void insert(Tipo_compra tipo){
			try(Connection con = lig.getConnection()){
				String sql="INSERT INTO tipo_compra (tipo) VALUES (?);";
				PreparedStatement cmd = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				cmd.setString(1,tipo.getTipo());
				cmd.execute();
				
				//pegar a chave gerada
				ResultSet key = cmd.getGeneratedKeys();
				if(key.next()){
					tipo.setId_tipo(key.getInt(1));
				}
				//fim da chave
				
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		
		public void insert(Clientes c){
			try(Connection con = lig.getConnection()){
				String sql="INSERT INTO clientes (nome, email,idade ) VALUES (?,?,?);";
				PreparedStatement cmd = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				cmd.setString(1,c.getNome());
				cmd.setString(2,c.getEmail());
				cmd.setInt(3,c.getIdade());
				cmd.execute();
				
				//pegar a chave gerada
				ResultSet key = cmd.getGeneratedKeys();
				if(key.next()){
					c.setId(key.getInt(1));
				}
				//fim da chave
				
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public void insert (Compras c){
			try(Connection con = lig.getConnection()){
				String sql="INSERT INTO compras (id_cliente, id_tipo,quantidade, preco, data_compra ) VALUES (?,?,?,?,?);";
				PreparedStatement cmd = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				cmd.setInt(1,c.getId_cliente());
				cmd.setInt(2,c.getId_tipo());
				cmd.setInt(3,c.getQuantidade());
				cmd.setDouble(4,c.getPreco());
				cmd.setDate(5,c.getData());
				cmd.execute();
				
				//pegar a chave gerada
				ResultSet key = cmd.getGeneratedKeys();
				if(key.next()){
					c.setId_compra(key.getInt(1));
				}
				//fim da chave
				
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		
		public boolean deleteTipo (int id){
			try(Connection con = lig.getConnection()){
				
				String sql="DELETE FROM tipo_compra WHERE id_tipo = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1,id);
				
				return cmd.execute();
				
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public boolean deleteCliente( int id){
			try(Connection con = lig.getConnection()){
				
				String sql="DELETE FROM clientes WHERE id_cliente = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1,id);
				return cmd.execute();
				
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public boolean deleteCompra(int id){
			try(Connection con = lig.getConnection()){
				
				String sql="DELETE FROM compras WHERE id_compra = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				cmd.setInt(1,id);
				return cmd.execute();
				
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		
		
		public Clientes selectCliente(int id){
			try(Connection con = lig.getConnection()){
				
				String sql="SELECT nome,email,idade FROM clientes WHERE id_cliente = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setInt(1,id);
				ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
				if(rs.next()){
					Clientes cliente = new Clientes();
					cliente.setId(id);
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setIdade(rs.getInt("idade"));
					return cliente;
				}
				return null;
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public Tipo_compra selectTipo(int id){
			try(Connection con = lig.getConnection()){
				
				String sql="SELECT tipo FROM tipo_compra WHERE id_tipo = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setInt(1,id);
				ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
				if(rs.next()){
					Tipo_compra tipo = new Tipo_compra();
					tipo.setId_tipo(id);
					tipo.setTipo(rs.getString("tipo"));
					return tipo;
				}
				return null; //Retorna null caso n�o exista o id
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		
		public Compras selectCompra (int id){
			try(Connection con = lig.getConnection()){
				String sql="SELECT cli.id_cliente,cli.nome, cli.email, cli.idade,t.id_tipo ,t.tipo ,comp.quantidade, comp.preco, comp.data_compra FROM compras AS comp INNER JOIN clientes AS cli ON (cli.id_cliente = comp.id_cliente) INNER JOIN tipo_compra AS t ON (t.id_tipo = comp.id_tipo) WHERE comp.id_compra = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setInt(1,id);
				ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
				if(rs.next()){
					Compras compras = new Compras();
					Clientes cliente = new Clientes();
					Tipo_compra tipo = new Tipo_compra();
					compras.setId_compra(id);
					compras.setQuantidade(rs.getInt("quantidade"));
					compras.setPreco(rs.getDouble("preco"));
					compras.setData(rs.getDate("data_compra"));
					cliente.setId(rs.getInt("id_cliente"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setIdade(rs.getInt("idade"));
					compras.setCliente(cliente);
					tipo.setId_tipo(rs.getInt("id_tipo"));
					tipo.setTipo(rs.getString("tipo"));
					compras.setTipo(tipo);
					return compras;
				}
				return null;
				}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public void updateTipo(Tipo_compra tipo){
			if(tipo.getId_tipo()==null)throw new RuntimeException("Id � nulo, tipo de compra n�o pode ser atualizado!");
			try(Connection con = lig.getConnection()){
				String sql="UPDATE tipo_compra SET tipo = ? WHERE id_tipo = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setString(1,tipo.getTipo());
				cmd.setInt(2,tipo.getId_tipo());
				cmd.execute();
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public void updateCliente(Clientes cliente){
			if(cliente.getId()==null)throw new RuntimeException("Id � nulo, cliente n�o pode ser atualizado!");
			try(Connection con = lig.getConnection()){
				String sql="UPDATE clientes SET nome = ?,email = ?,idade = ? WHERE id_cliente = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setString(1,cliente.getNome());
				cmd.setString(2,cliente.getEmail());
				cmd.setInt(3,cliente.getIdade());
				cmd.setInt(4,cliente.getId());
				cmd.execute();
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		public void updateCompra(Compras compra){
			if(compra.getId_compra()==null)throw new RuntimeException("Id � nulo, compra n�o pode ser atualizada!");
			try(Connection con = lig.getConnection()){
				String sql="UPDATE compras SET id_cliente = ?, id_tipo=?, quantidade = ?,preco = ?,data_compra = ? WHERE id_compra = ?;";
				PreparedStatement cmd = con.prepareStatement(sql);
				
				cmd.setInt(1,compra.getId_cliente());
				cmd.setInt(2,compra.getId_tipo());
				cmd.setInt(3,compra.getQuantidade());
				cmd.setDouble(4,compra.getPreco());
				cmd.setDate(5,compra.getData());
				cmd.setInt(6,compra.getId_compra());
				cmd.execute();
			}catch (Exception e){
				throw new RuntimeException(e);
			
		}
		
	}	
	public List<Clientes> selectPageClientes(int pagina){
		try(Connection con = lig.getConnection()){
			String sql="SELECT id_cliente,nome,email, idade FROM clientes ORDER BY nome,idade DESC OFFSET  ? LIMIT "+LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1)*LIMIT;
			cmd.setInt(1,offset);
			ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
			
			List<Clientes> clientes = new ArrayList();
			
			while(rs.next()){
				Clientes cliente = new Clientes();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setIdade(rs.getInt("idade"));
				clientes.add(cliente);
			}
			return clientes;
			}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	public List<Tipo_compra> selectPageTipo(int pagina){
		try(Connection con = lig.getConnection()){
			String sql="SELECT id_tipo,tipo FROM tipo_compra ORDER BY tipo DESC OFFSET ? LIMIT "+LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1)*LIMIT;
			cmd.setInt(1,offset);
			ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
			
			List<Tipo_compra> tipos = new ArrayList();
			
			while(rs.next()){
				Tipo_compra tipo = new Tipo_compra();
				tipo.setId_tipo(rs.getInt("id_tipo"));
				tipo.setTipo(rs.getString("tipo"));
				tipos.add(tipo);
			}
			return tipos;
			}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	public List<Compras> selectPageCompras(int pagina){
		try(Connection con = lig.getConnection()){
			String sql="SELECT cli.id_cliente,cli.nome, cli.email, cli.idade,t.id_tipo ,t.tipo ,comp.id_compra,comp.quantidade, comp.preco, comp.data_compra FROM compras AS comp INNER JOIN clientes AS cli ON (cli.id_cliente = comp.id_cliente) INNER JOIN tipo_compra AS t ON (t.id_tipo = comp.id_tipo)  ORDER BY cli.nome,cli.idade DESC OFFSET  ? LIMIT "+LIMIT;
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1)*LIMIT;
			cmd.setInt(1,offset);
			ResultSet rs = cmd.executeQuery(); //consulta - devolve um ResultSet
			
			List<Compras> compras = new ArrayList();
			
			while(rs.next()){
				Compras compra = new Compras();
				Clientes cliente = new Clientes();
				Tipo_compra tipo = new Tipo_compra();
				compra.setId_compra(rs.getInt("id_compra"));
				compra.setQuantidade(rs.getInt("quantidade"));
				compra.setPreco(rs.getDouble("preco"));
				compra.setData(rs.getDate("data_compra"));
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setIdade(rs.getInt("idade"));
				compra.setCliente(cliente);
				tipo.setId_tipo(rs.getInt("id_tipo"));
				tipo.setTipo(rs.getString("tipo"));
				compra.setTipo(tipo);
				compras.add(compra);
			}
			return compras;
			}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
