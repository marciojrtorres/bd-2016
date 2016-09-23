package laurad.model;

public class Jogo {
	
	private String nome;
	private Integer idJogo;
	
	public Jogo() {
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idJogo == null) ? 0 : idJogo.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (idJogo == null) {
			if (other.idJogo != null)
				return false;
		} else if (!idJogo.equals(other.idJogo))
			return false;
		return true;
	}	
	public String toString() {
		return "Jogo [nome=" + nome + ", idJogo=" + idJogo + "]";
	}
		
}
