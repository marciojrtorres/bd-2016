package laurag.model;

import java.util.*;

public class Aluno {

	private Integer id;
	private String nome;
	private String sobrenome;
	private String sexo;
	private List<Disciplina> disciplinas = new ArrayList();
	private List<Matricula> matriculas = new ArrayList();

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void addDisciplina(Disciplina d) {
		disciplinas.add(d);
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void addMatricula(Matricula m) {
		matriculas.add(m);
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", sexo=" + sexo + "]";
	}
}