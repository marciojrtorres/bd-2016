package marcos.DAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import marcos.DB;
import marcos.model.Empresa;
import marcos.model.Funcionario;

public class FuncionarioDAO {
	
	private DB db = new DB();
	
	public void insert(Funcionario f) {
		try (Connection con = db.getConnection()) {
			String sql = "INSERT INTO funcionario" 
					+ "(nomeFuncionario, cpf, rg, endereco, dataEntrada, cargo, genero)VALUES"
					+"('?', '?', '?', '?', '?', '?','?');";
			
			PreparedStatement cmd = 
con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			List<Empresa> idsEmpresas = EmpresaDAO.selectAllIdEmpresa();
			boolean a = false;
			for(int i = 0; i < idsEmpresas.size(); i++){
				if(idsEmpresas.subList(i, i++).equals(f.getIdEmpresa()))a = true;
			}
			if(a == false)throw new RuntimeException("Empresa n�o cadastrada");
			
			cmd.setInt(1, f.getIdEmpresa());
			cmd.setString(2, f.getNomeFuncionario());
			cmd.setString(3, f.getCPF());
			cmd.setString(4, f.getRg());
			cmd.setString(5, f.getEndereco());
			cmd.setDate(6, f.getDataEntrada());
			cmd.setString(7, f.getCargo());
			cmd.setString(8, String.valueOf(f.getGenero()));
				
			cmd.execute();
			// pegar a chave gerada
			ResultSet key = cmd.getGeneratedKeys();
			if (key.next()) {
				f.setIdFuncionario(key.getInt(1));
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Funcionario select(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nomeFuncionario, idEmpresa, cargo  FROM funcionario"
					+ "funcionario JOIN empresa ON funcionario.idEmpresa=empresa.idEmpresa"+
					 "WHERE idFuncionario = '?';";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(id);
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				funcionario.setIdEmpresa(rs.getInt("idEmpresa"));
				funcionario.setCargo(rs.getString("cargo"));
				return funcionario;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Funcionario funcionario) {
		if (funcionario.getIdFuncionario() == null) {
			throw new RuntimeException("O id eh nulo, nao pode atualizar");
		}
		
		try (Connection con = db.getConnection()) {
			String sql =  "UPDATE funcionario"
					+ "SET idEmpresa='?', nomeFuncionario='?', cpf='?', rg='?', endereco='?', dataEntrada='?', cargo='?', genero='?'"
					 + "WHERE idFuncionario = '?';";
			
			PreparedStatement cmd = 
				con.prepareStatement(sql);
			
			cmd.setInt(1, funcionario.getIdEmpresa());
			cmd.setString(2, funcionario.getNomeFuncionario());
			cmd.setString(3, funcionario.getCPF());
			cmd.setString(4, funcionario.getRg());
			cmd.setString(5, funcionario.getEndereco());
			cmd.setDate(6, funcionario.getDataEntrada());
			cmd.setString(7, funcionario.getCargo());
			cmd.setString(8, String.valueOf(funcionario.getGenero()));
			cmd.setInt(9, funcionario.getIdFuncionario());
			cmd.execute();
			
			sql = "DELETE FROM funcionario "
				+ "WHERE id_contato = ?";
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, funcionario.getIdFuncionario());
			cmd.execute();
			
			// reinserindo
			sql = "INSERT INTO funcionario VALUES (?,?,?,?,?,?,?,?,?)";
	
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, funcionario.getIdFuncionario());
			cmd.setInt(2, funcionario.getIdEmpresa());
			cmd.setString(3, funcionario.getNomeFuncionario());
			cmd.setString(4, funcionario.getCPF());
			cmd.setString(5, funcionario.getRg());
			cmd.setString(6, funcionario.getEndereco());
			cmd.setDate(7, funcionario.getDataEntrada());
			cmd.setString(8, funcionario.getCargo());
			cmd.setString(9, String.valueOf(funcionario.getGenero()));
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public boolean delete(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "DELETE FROM funcionario WHERE id = ?";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Funcionario selectbyNome(int id) {
		try (Connection con = db.getConnection()) {
			String sql = "SELECT nomeFuncionario  FROM funcionario"
					+ "WHERE idFuncionario = '?';";
			PreparedStatement cmd = 
						con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery(); // consulta devolve um ResultSet
			
			if (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(id);
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				return funcionario;
			}
			
			return null;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
