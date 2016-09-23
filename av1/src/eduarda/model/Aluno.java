package eduarda.model;

import java.sql.Date;

public class Aluno {
	private Integer id;
	private Integer matricula;
	private String nome;
	private Genero genero;
	private Date data_nascimento;
	public Aluno(){
		
	}	
	
	public Aluno(Integer matricula, String nome, Genero genero, Date data_nascimento) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.genero = genero;
		this.data_nascimento = data_nascimento;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Integer getId() {
		return id;
	}
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", genero=" + genero
				+ ", data_nascimento=" + data_nascimento + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
