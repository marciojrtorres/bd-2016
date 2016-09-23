package laurag.model;

import java.util.*;

public class Disciplina {

	private Integer id;
	private String nome;
	private int c_hora;
	private List<Aluno> alunos = new ArrayList();

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void addAluno(Aluno a) {
		alunos.add(a);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getC_hora() {
		return c_hora;
	}

	public void setC_hora(int c_hora) {
		this.c_hora = c_hora;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", c_hora=" + c_hora + "]";
	}
}