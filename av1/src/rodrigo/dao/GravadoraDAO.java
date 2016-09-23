package rodrigo.dao;



	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rodrigo.SpotFavela.DB;
import rodrigo.model.Banda;
import rodrigo.model.Gravadora;

	// CRUD
	// CREATE / READ   / UPDATE / DELETE
	// INSERT / SELECT / UPDATE / DELETE

	public class GravadoraDAO { // Data Access Object
		
		private static final int LIMIT = 2;
		
		private DB db = new DB();
		public void insert(Gravadora g){
			try (Connection con = db.getConnection()) {
				String sql = "INSERT INTO gravadoras "
						+ "(nome, qtde_bandas,data_fundacao) VALUES "
						+ "(?, ?,?);";

				
				PreparedStatement cmd = 
	con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				cmd.setString(1, g.getNome());
				cmd.setInt(2, g.getQtde_bandas());
				cmd.setDate(3, g.getData());
				cmd.execute();
				// pegar a chave gerada
				ResultSet key = cmd.getGeneratedKeys();
				if (key.next()) {
					g.setId(key.getInt(1));
				}
				
				// c = g
				// t = b
			
				//System.out.println(g.getBandas());
				String sqlBandas = 
						"INSERT INTO Bandas "
								+ "(nome, genero,qtde_albums,id_gravadoras,nacionalidade) VALUES "
								+ "(?, ?,?,?,nac (?));";
				for (Banda b : g.getBandas()) {
					PreparedStatement cmdBan = 
							con.prepareStatement(sqlBandas);
					
					cmdBan.setString(1, b.getNome());
					cmdBan.setString(2,b.getGenero());
					cmdBan.setInt(3, b.getqtde_albums());
					cmdBan.setInt(4,g.getId());
					//System.out.println(b.getNacionalidade().toString());
					cmdBan.setString(5,b.getNacionalidade().toString());
					
					cmdBan.execute();
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
			// inserindo os telefones
						
		}
	
		public boolean delete(int id) {
			try (Connection con = db.getConnection()) {
			
				String sqlband = "DELETE FROM bandas WHERE id = ?";
				PreparedStatement cmdbandas = con.prepareStatement(sqlband);
				cmdbandas.setInt(1,id);
				cmdbandas.execute();
				
				String sqlgrav = "DELETE FROM gravadoras WHERE id = ?";
				PreparedStatement cmdgrav = con.prepareStatement(sqlgrav);
				cmdgrav.setInt(1,id);
				cmdgrav.execute();
				
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return false;
		}
		public void update(Gravadora g) {
			if (g.getId() == null) {
				throw new RuntimeException("O id eh nulo, nao pode atualizar");
			}
			//nome, qtde_bandas,data_fundacao)
			try (Connection con = db.getConnection()) {
				String sql = "UPDATE gravadoras "
				   		   + "SET nome = ?, qtde_bandas  = ?, data_fundacao = ? "
						   + "WHERE id = ?";
				
				PreparedStatement cmd = 
					con.prepareStatement(sql);
				
				cmd.setString(1, g.getNome());
				cmd.setInt(2, g.getQtde_bandas());
				cmd.setDate(3, g.getData());
				cmd.setInt(4, g.getId());
				cmd.execute();
				
				sql = "DELETE FROM bandas "
					+ "WHERE id_gravadoras = ?";
				cmd = con.prepareStatement(sql);
				cmd.setInt(1, g.getId());
				cmd.execute();
				
				// reinserindo
				String sqlBandas = 
						"INSERT INTO Bandas "
								+ "(nome, genero,qtde_albums,id_gravadoras) VALUES "
								+ "(?, ?,?,?);";
				for (Banda b : g.getBandas()) {
					PreparedStatement cmdBan = 
							con.prepareStatement(sqlBandas);
					
					cmdBan.setString(1, b.getNome());
					cmdBan.setString(2,b.getGenero());
					cmdBan.setInt(3, b.getqtde_albums());
					cmdBan.setInt(4,g.getId());
					
					cmdBan.execute();
				}
				
				
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		public List<Gravadora> selectPage(int pagina) {
			try (Connection con = db.getConnection()) {
				String sql = "SELECT id, nome, qtde_bandas,data_fundacao "
						   + "FROM gravadoras "
						   + "ORDER BY nome DESC "
						   + "OFFSET ? "
						   + "LIMIT " + LIMIT;
				PreparedStatement cmd = 
							con.prepareStatement(sql);
				int offset = (pagina - 1) * LIMIT;
				
				cmd.setInt(1, offset);
				
				ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
				
				List<Gravadora> gravadoras = new ArrayList();
				
				while (rs.next()) {
					//qtde_bandas,data_fundacao 
					Gravadora g = new Gravadora();
					g.setId(rs.getInt("id"));
					g.setNome(rs.getString("nome"));
					g.setQtde_bandas(rs.getInt("qtde_bandas"));
					gravadoras.add(g);
				}
				
				return gravadoras;
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		public void selectBandas(Gravadora g) {
			if (g.getId() == null) {
				throw new RuntimeException("O id eh nulo, nao pode carregar bandas");
			}
			try (Connection con = db.getConnection()) {
				String sql = "SELECT nome, genero, qtde_albums, nacionalidade FROM bandas WHERE id_gravadoras = ?";
				PreparedStatement cmd = 
							con.prepareStatement(sql);
				
				cmd.setInt(1, g.getId());
				
				ResultSet rs = cmd.executeQuery();
				
				while (rs.next()) {
					Banda b = new Banda();
					b.setNome(rs.getString("nome"));
					b.setGenero(rs.getString("genero"));
					b.setqtde_albums(rs.getInt("qtde_albums"));
					b.setNacionalidade(rs.getString("nacionalidade"));
					g.addBanda(b);
					return;
				}
							
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		public List<Gravadora> selectnome(String nome) {
			try (Connection con = db.getConnection()) {
				String sql = "SELECT nome, qtde_bandas, data_fundacao, id FROM gravadoras WHERE nome = ?;";
				PreparedStatement cmd = 
							con.prepareStatement(sql);
				cmd.setString(1, nome);
				
				ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
				List<Gravadora> gravadoras = new ArrayList();
				while (rs.next()) {
					//qtde_bandas,data_fundacao 
					Gravadora g = new Gravadora();
					g.setId(rs.getInt("id"));
					g.setNome(rs.getString("nome"));
					g.setQtde_bandas(rs.getInt("qtde_bandas"));
					gravadoras.add(g);
				}
				return gravadoras;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
				
		public Gravadora select(int id) {
			try (Connection con = db.getConnection()) {
				String sql = "SELECT nome, qtde_bandas, data_fundacao "
						   + "FROM gravadoras WHERE id = ?";
				PreparedStatement cmd = 
							con.prepareStatement(sql);
				cmd.setInt(1, id);
				
				ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
				
				if (rs.next()) {
					Gravadora g = new Gravadora();
					g.setId(id);
					g.setNome(rs.getString("nome"));
					g.setQtde_bandas(rs.getInt("qtde_bandas"));
					g.setData(rs.getDate("data_fundacao"));
					return g;
				}
				
				return null;
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			
		}
	}
