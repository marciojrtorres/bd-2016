package anacarolina.model;

public class Curso {
	
	private Integer id_curso;
	private String nome;
	private String coordenador;
	private Enum modalidade;
	
	
	
	public enum Modalidades {
		Integrado, Concomitante, Subsequente;
	}
	
	
	public Enum getModalidade() {
		return modalidade;
	}

	public void setModalidade(Enum modalidade) {
		this.modalidade = modalidade;
	}
	
	public Enum StringEnum(String stringmodalidade) {
		
		Modalidades enummodalidade = Modalidades.valueOf(stringmodalidade);
		return enummodalidade;	
	}
	public Integer getId_curso() {
		return id_curso;
	}
	
	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCoordenador() {
		return coordenador;
	}
	
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}
	


	@Override
	public String toString() {
		return "Curso [id_curso=" + id_curso + ", nome=" + nome + ", coordenador=" + coordenador + ", modalidade="
				+ modalidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_curso == null) ? 0 : id_curso.hashCode());
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
		Curso other = (Curso) obj;
		if (id_curso == null) {
			if (other.id_curso != null)
				return false;
		} else if (!id_curso.equals(other.id_curso))
			return false;
		return true;
	}
	

}
