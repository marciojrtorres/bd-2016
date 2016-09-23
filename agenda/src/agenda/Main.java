package agenda;

import java.util.List;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;
import agenda.model.Telefone;

public class Main {
	public static void main(String[] args) {
		// TRANSIENTE (em memória)
		Contato c = new Contato();
		c.setNome("Rodrigo");
		c.setSobrenome("Gandra");
		
		c.addTelefone(new Telefone("53", "88339922"));
		c.addTelefone(new Telefone("54", "55882233"));
		
		ContatoDAO dao = new ContatoDAO();
		// PERSISTENTE (em disco)
		dao.insert(c);
		
		System.out.println(dao.selectAll());
		
		System.out.println(dao.selectByNome("ogro"));
		
	}
}












