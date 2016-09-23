package joaovitor.locadora;

import java.sql.Date;
import java.util.List;

import joaovitor.dao.ClienteDAO;
import joaovitor.model.Cliente;
import joaovitor.model.PagDia;

public class Main {

	public static void main(String[] args) {

		Cliente c = new Cliente();
		c.setNome("Marcos");
		c.setSobrenome("Goulart");
		c.setEmail("Marcos.goulart@gmail.com");
		Date data = Date.valueOf("1999-02-05");
		c.setData_nasc(data);
		c.setPagdia(PagDia.sim);

		ClienteDAO dao = new ClienteDAO();
		dao.delete(1);

		Cliente ig = dao.select(2);
		System.out.println(ig);

		Cliente teste = dao.select(7777);
		if (teste == null) {
			System.out.println("nao existe");
		} else {
			System.out.println(teste);
		}

		List<Cliente> todos = dao.selectAll();

		System.out.println(todos);

		List<Cliente> pag1 = dao.selectPage(1);
		System.out.println(pag1);

		Cliente clienteupdate = dao.select(11);
		clienteupdate.setSobrenome("Silva");
		dao.update(clienteupdate);

	}

}
