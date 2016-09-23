package marcos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marcos.DB;
import marcos.model.Empresa;

public class EmpresaDAO {

	
	public static List <Empresa> selectAllIdEmpresa() {
		
		 DB db = new DB();
		
		try (Connection con = db.getConnection()) {
			String sql = "SELECT idEmpresa"
					   + "FROM empresa ORDER BY idEmpresa DESC";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
		
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			List<Empresa> ids = new ArrayList();
			
			while (rs.next()) {
				Empresa id = new Empresa();
				id.setIdEmpresa(rs.getInt("idEmpresa"));
				ids.add(id);
			}
			
			return ids;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Empresa select(int id) {
		 DB db = new DB();
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nomeEmpresa, cnpj "
					   + "FROM empresa WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(id);
				empresa.setNomeEmpresa("nomeEmpresa");
				return empresa;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void insert(Empresa empresa) {
		DB db = new DB();
	
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO contatos "
					+ "(nome, sobrenome) VALUES "
					+ "(?, ?);";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, empresa.getNomeEmpresa());
			cmd.setString(2, empresa.getCnpj());
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				empresa.setIdEmpresa(key.getInt(1));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Empresa empresa) {
		DB db = new DB();
		if (empresa.getIdEmpresa() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql = "UPDATE empresa "
			   		   + "SET nomeEmpresa = ?, cnpj = ? "
					   + "WHERE idEmpresa = ?";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setString(1, empresa.getNomeEmpresa());
			cmd.setString(2, empresa.getCnpj());
			cmd.setInt(3, empresa.getIdEmpresa());
			cmd.execute();
			
			sql = "DELETE FROM empresa "
				+ "WHERE edEmpresa = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, empresa.getIdEmpresa());
			cmd.execute();
			
			// reinserindo
			sql = "INSERT INTO telefones VALUES (?,?,?)";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, empresa.getIdEmpresa());
			cmd.setString(2, empresa.getNomeEmpresa());
			cmd.setString(3, empresa.getCnpj());
			cmd.execute();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean delete(int id) {
		 DB db = new DB();
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM empresa WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}