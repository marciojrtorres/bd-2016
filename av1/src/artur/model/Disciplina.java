package artur.model;

import java.util.*;

public class Disciplina {
	private String nome;
	private String ramo;
	private Professor professor;
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	@Override
	public String toString() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ramo == null) ? 0 : ramo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ramo == null) {
			if (other.ramo != null)
				return false;
		} else if (!ramo.equals(other.ramo))
			return false;
		return true;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void addAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public void removeAluno(Aluno aluno) {
		alunos.remove(aluno);
	}
	
	public void removeAluno(Integer id) {
		alunos.remove(id);
	}
}