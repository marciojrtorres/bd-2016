package eduarda;

import java.sql.Date;

import eduarda.dao.AlunoDAO;
import eduarda.dao.CursoDAO;
import eduarda.dao.MatriculaDAO;
import eduarda.model.Aluno;
import eduarda.model.Curso;
import eduarda.model.Genero;
import eduarda.model.Matricula;

public class Main {
	public static void main(String[] args){
		
		Aluno joana = new Aluno();
		joana.setGenero(Genero.feminino);
		joana.setMatricula(1198652);
		joana.setNome("Joana");
		joana.setData_nascimento(Date.valueOf("1999-9-2"));
		
		Aluno caio = new Aluno();
		caio.setGenero(Genero.masculino);
		caio.setMatricula(11986378);
		caio.setNome("Caio");
		caio.setData_nascimento(Date.valueOf("1996-10-20"));
		
		Aluno pedro = new Aluno();
		pedro.setGenero(Genero.masculino);
		pedro.setMatricula(11527903);
		pedro.setNome("Pedro");
		pedro.setData_nascimento(Date.valueOf("1997-07-25"));
		
		AlunoDAO adao = new AlunoDAO();
		
//		adao.insert(joana);
//		adao.insert(caio);
//		adao.insert(pedro);
		
		System.out.println(adao.select(1));
		System.out.println(adao.selectAll());
		
		System.out.println(adao.delete(51));
		System.out.println(adao.select(51));
		
		System.out.println(adao.select("Caio"));
		
//		Aluno alunoupdate = adao.selectAll().get(0);
//		System.out.println(alunoupdate.getNome());
//		alunoupdate.setNome("Jo�o");
//		System.out.println(alunoupdate.getNome());
//		adao.update(alunoupdate);
//		
//		System.out.println(alunoupdate);
		
		System.out.println(adao.selectPage(3));
		
		Curso info = new Curso();
		info.setNome("Inform�tica");
		info.setDuracao(3);
		
		Curso geo = new Curso();
		geo.setNome("Geoprocessamento");
		geo.setDuracao(2);
		
		Curso eletro = new Curso();
		eletro.setNome("Eletrot�cnica");
		eletro.setDuracao(4);
		
		CursoDAO cdao = new CursoDAO();
		
//		cdao.insert(info);
//		cdao.insert(eletro);
//		cdao.insert(geo);
		
		System.out.println(cdao.select(1));
		System.out.println(cdao.select("Inform�tica"));
		System.out.println(cdao.selectAll());
		
		System.out.println(cdao.delete(1));
		System.out.println(cdao.select(1));
		
		Curso cursoupdate = cdao.selectAll().get(2);
		System.out.println(cursoupdate.getNome());
		cursoupdate.setNome("Inform�tica The Best");
		cursoupdate.setDuracao(3);
		cdao.update(cursoupdate);
		System.out.println(cursoupdate);
		
		Matricula m = new Matricula();
		m.setId_aluno(3);
		m.setId_curso(4);
		m.setData_matricula(Date.valueOf("2015-03-17"));
		
		Matricula a = new Matricula();
		a.setId_aluno(2);
		a.setId_curso(5);
		a.setData_matricula(Date.valueOf("2015-03-17"));
		
		MatriculaDAO mdao = new MatriculaDAO();
		
//		mdao.insert(m);
//		mdao.insert(a);
		
		System.out.println(mdao.selectAll());
		System.out.println(mdao.selectPage(2));
		
		System.out.println(mdao.delete(1));
		System.out.println(mdao.selectAll());
		
		Matricula matriculaupdate = mdao.selectAll().get(1);
		System.out.println(matriculaupdate.getData_matricula());
		matriculaupdate.setData_matricula(Date.valueOf("2014-03-17"));
		mdao.update(matriculaupdate);
		System.out.println(matriculaupdate);
		
	}
}
