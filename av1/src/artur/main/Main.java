package artur.main;

import java.util.Date;

import artur.dao.AlunoDAO;
import artur.dao.DisciplinaDAO;
import artur.dao.ProfessorDAO;
import artur.model.Aluno;
import artur.model.Disciplina;
import artur.model.Professor;
import artur.model.Sexo;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aluno a = new Aluno();
		//a.setId(1);
		a.setNome("teste");
		a.setNascimento(new Date(29, 8, 120));
		a.setSexo(Sexo.FEMININO);
		a.setEndereco("Rua A, 222, BGV");
		a.setTelefone("22222222");
		a.setEmail("abc@def.gh");
		
		AlunoDAO adao = new AlunoDAO();
		adao.insert(a);
		
		System.out.println(a.getId());
		
		Professor p = new Professor();
		//p.setId(1);
		p.setNome("teste2");
		p.setNascimento(new Date(29, 8, 120));
		p.setSexo(Sexo.FEMININO);
		p.setEndereco("Rua A, 222, BGV");
		p.setTelefone("22222222");
		p.setEmail("teste2@def.gh");
		
		ProfessorDAO pdao = new ProfessorDAO();
		pdao.insert(p);
		
		System.out.println(p.getId());
		
		Disciplina d = new Disciplina();
		//d.setId(0);
		d.setNome("Matem�tica");
		d.setRamo("Exatas");
		d.setProfessor(p);
		
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.insert(d);
		System.out.println(d.getId());
		
		d.addAluno(a);
		
		dao.update(d);
		
		System.out.println("Processo bem-sucedido!");
	}
}