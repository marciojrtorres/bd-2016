package agenda;

import java.util.List;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;
import agenda.model.Telefone;

public class Main {
	public static void main(String[] args) {
		
		Contato c = new Contato();
		c.setNome("Marcos");
		c.setSobrenome("Goulart");
		
		Telefone t1 = new Telefone();
		t1.setDdd("53");
		t1.setNumero("77223333");
		c.addTelefone(t1);
		
		Telefone t2 = new Telefone();
		t2.setDdd("53");
		t2.setNumero("32323233");
		c.addTelefone(t2);
		
		System.out.println(c); // id = null!
		
		ContatoDAO dao = new ContatoDAO();
		// dao.insert(c);
		
		System.out.println(c); // id != null!
		
		// ID = 1
		dao.delete(1); // delete by primary key
		
		Contato ig = dao.select(2);
		
		System.out.println(ig); // Igor Bira
		
		System.out.println(ig.getTelefones());
		
		Contato teste = dao.select(773438);
		
		System.out.println(teste);
		
		if (teste == null) System.out.println("nao existe");
		else System.out.println(teste);

		List<Contato> todos = dao.selectAll();
		
		System.out.println(todos);
		
		List<Contato> pag1 = dao.selectPage(1);
		System.out.println(pag1);
		
		List<Contato> pag2 = dao.selectPage(2);
		System.out.println(pag2);
		
		
		
		
		
	}
}






