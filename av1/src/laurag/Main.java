package laurag;

import java.util.*;

import laurag.dao.*;
import laurag.model.*;

public class Main {
	public static void main(String[] args) {

		EscolaDAO dao = new EscolaDAO();

		// ADICIONA ALUNOS
		Aluno a = new Aluno();
		a.setNome("Victor");
		a.setSobrenome("Colares");
		a.setSexo("M");
		dao.insert(a);

		Aluno a2 = new Aluno();
		a2.setNome("Gregory");
		a2.setSobrenome("Magnus");
		a2.setSexo("M");
		dao.insert(a2);

		Aluno a3 = new Aluno();
		a3.setNome("Bruno");
		a3.setSobrenome("Ribeiro");
		a3.setSexo("M");
		dao.insert(a3);

		// ADICIONA DISCIPLINAS
		Disciplina d = new Disciplina();
		d.setNome("Banco de Dados");
		d.setC_hora(50);
		dao.insertDisciplina(d);

		// ADICIONA A MATRICULA DOS ALUNOS + MATRICULA NO BANCO
		Matricula m = new Matricula();
		m.setId_aluno(7);
		m.setId_disciplina(5);
		m.setInscricao(new java.sql.Date(116, 7, 1));
		a.addMatricula(m);
		dao.insertMatricula(m);

		Matricula m2 = new Matricula();
		m2.setId_aluno(7);
		m2.setId_disciplina(3);
		m2.setInscricao(new java.sql.Date(115, 4, 21));
		a.addMatricula(m2);
		dao.insertMatricula(m2);

		// EXCLUI ALUNOS (ALUNO E ID)
		dao.delete(a2);
		dao.delete(9);

		Aluno a4 = new Aluno();
		a4.setNome("Ana Carolina");
		a4.setSobrenome("Souza");
		a4.setSexo("F");
		dao.insert(a4);

		// SELECIONA ALUNO (ID, SELECT ALL, MATRICULAS, PAGINADO)
		Aluno select = dao.select(1);
		System.out.println(select);

		List<Aluno> todos = dao.selectAll();
		System.out.println(todos);

		List<Matricula> a_matriculas = dao.selectMatriculas(a);
		System.out.println(a_matriculas);

		List<Aluno> pag = dao.selectPage(2);
		System.out.println(pag);

		// ATUALIZA ALUNO
		Aluno aluno_update = dao.select(2);
		System.out.println(aluno_update);
		aluno_update.setNome("Ana Fl�via");
		aluno_update.setSobrenome("Moraes");
		aluno_update.setSexo("F");
		dao.update(aluno_update);
		System.out.println(aluno_update);
	}
}