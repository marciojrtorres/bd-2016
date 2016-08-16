package agenda;

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
		dao.insert(c);
		
		System.out.println(c); // id != null!
		
	}
}






