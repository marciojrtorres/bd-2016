package anacarolina;

import java.sql.Date;
import java.util.List;

import anacarolina.dao.AlunoDAO;
import anacarolina.dao.CursoDAO;
import anacarolina.model.Aluno;
import anacarolina.model.Curso;
import anacarolina.model.Curso.Modalidades;

public class Main {


	public static void main(String[] args) { 
		
		// C-R-U-D
		
		Curso c = new Curso();
		c.setNome("Automa��o Industrial");
		c.setCoordenador("Carlos Rodrigues Rocha");
		c.setModalidade(Modalidades.Integrado);
		
		Curso c2 = new Curso();
		c2.setNome("Refrigera��o");
		c2.setCoordenador("Carlos Rodrigues Rocha");
		c2.setModalidade(Modalidades.Integrado);
		
		Aluno a = new Aluno();
		a.setMatricula("11040708");
		a.setNome("J�lia");
		a.setSobrenome("Mendes");
		Date dt = new Date(23, 02, 2013);
		a.setData_inicio(dt);
		a.setId_curso(5);
		

		// aluno com curso inexistente
		/*
		Aluno a3 = new Aluno();
		a.setMatricula("11050708");
		a.setNome("Gabriela");
		a.setSobrenome("Mendes");
		Date dt2 = new Date(23, 02, 2013);
		a.setData_inicio(dt);
		a.setId_curso(9);
		*/
		
		CursoDAO dao = new CursoDAO();
		AlunoDAO dao2 = new AlunoDAO();
		
		
		// CREATE
		//dao.insert(c2); // inserindo um curso
		//dao2.insert(a); // inserindo um aluno
		
	
		
		// READ
		Curso curso3 = dao.select(1);
		//System.out.println(curso3.toString()); // Inform�tica para Internet
		Aluno aluno3 = dao2.select(3);
		//System.out.println(aluno3.toString()); // Ana Carolina
		
		
		
		// UPDATE
		Aluno aluno2 = dao2.select(25);
		//aluno2.setNome("Vanessa");
		//dao2.update(aluno2); // update de aluno
		
		
		Curso curso2 = dao.select(7);
		//curso2.setModalidade(Modalidades.Concomitante);
		//dao.update(curso2); // update de curso
		
		
		// DELETE
		//dao2.delete(25); // deletando um aluno
		//dao.delete(7); // deletando um curso
		

		List<Integer> ids = dao.selectId(); // pega os ids de todos os cursos
		
		// SELECT DE PAGINA
		// ordem DESC
		//System.out.println(dao.selectPage(1)); // mostra a primeira p�gina dos cursos
		//System.out.println(dao.selectPage(2)); // mostra a segunda p�gina dos cursos
		//System.out.println(dao2.selectPage(1)); // mostra a primeira p�gina dos alunos
		//System.out.println(dao2.selectPage(2)); // mostra a segunda p�gina dos alunos
		
		// SELECT ALL
		//System.out.println(dao.selectAll()); // mostrar todos os cursos
		//System.out.println(dao2.selectAll()); // mostrar todos os alunos
		
		
		// SELECT CRITERIADO
		//System.out.println(dao2.selectById_curso(1)); // Seleciona alunos pelo id_curso 
		//System.out.println(dao.selectByModalidade(Modalidades.Integrado)); // Seleciona cursos pela modalidade
	
	}
}
