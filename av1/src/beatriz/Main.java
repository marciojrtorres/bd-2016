package beatriz;

import java.sql.Date;
import java.util.Calendar;

import beatriz.dao.EscritorDAO;
import beatriz.dao.LivroDAO;
import beatriz.model.Escritor;
import beatriz.model.Genero;
import beatriz.model.Livro;

public class Main {
	public static void main(String[] args) {
	
		//Adicionando escritores
		EscritorDAO dao = new EscritorDAO();
	
		Escritor e1 = new Escritor();
		e1.setNome("Stephanie");
		e1.setSobrenome("Meyer");
		e1.setCep(96212110);
		System.out.println(e1);
		dao.insert(e1);
		 
		Escritor e2 = new Escritor();
		e2.setNome("J.K");
		e2.setSobrenome("Rowling");
		e2.setCep(96212111);
		System.out.println(e2);
		dao.insert(e2);
		
		//Adicionando livros
		LivroDAO daol = new LivroDAO();
		int dia = 11;
		int mes = 9;
		int ano = 2001;
		
		Date data = new Date((ano)-1900, (mes) -1 , 5);
		System.out.println(data);
		Livro l1 = new Livro();
		l1.setId_escritor(1);
		l1.setTitulo("Crepusculo");
		l1.setPublicacao(data);
		l1.setLivro_genero("romance");
		System.out.println(l1);
		daol.insert(l1);
		System.out.println(l1);
	
		int ano1 = 2002;
		Date data1 = new Date((ano1)-1900, (mes) -1 , 5);
		System.out.println(data1);
		Livro l2 = new Livro();
		l2.setId_escritor(2);
		l2.setTitulo("Harry Potter");
		l2.setPublicacao(data1);
		l2.setLivro_genero("terror");
		System.out.println(l2);
		daol.insert(l2);
		System.out.println(l2);

		//Deletndo pelo id do escritor
		dao.delete(1);
	
		//Deletando pelo titulo do livro
		daol.delete("Harry Potter");
	
		//Fazendo update
		EscritorDAO daoe =  new EscritorDAO();
		Escritor escritorupdate = daoe.selectAll().get(0);
		System.out.println(escritorupdate);
		escritorupdate.setNome("J. K.");
		daoe.update(escritorupdate);
		System.out.println(escritorupdate);
	
		// Select pelo id do escritor
		Escritor a = dao.select(2);
		System.out.println(a);
		
		//Select pelo titulo do livro
		Livro b = daol.select("Crepusculo");
		System.out.println(b);
	
		
		
		
		
	}
}
