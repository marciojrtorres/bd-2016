package jeniffer.model;

public class Serie{
	private String nome;
	private Integer temporada;
	private Integer episodio;
	
	
	public Serie(){}
	
	public Serie(String nome, Integer temporada, Integer episodio) {
		this.nome = nome;
		this.temporada = temporada;
		this.episodio = episodio;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getTemporada() {
		return temporada;
	}
	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}
	public Integer getEpisodio() {
		return episodio;
	}
	public void setEpisodio(Integer episodio) {
		this.episodio = episodio;
	}
	@Override
	public String toString() {
		return "Serie [nome=" + nome + ", temporada=" + temporada + ", episodio=" + episodio + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((episodio == null) ? 0 : episodio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((temporada == null) ? 0 : temporada.hashCode());
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
		Serie other = (Serie) obj;
		if (episodio == null) {
			if (other.episodio != null)
				return false;
		} else if (!episodio.equals(other.episodio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (temporada == null) {
			if (other.temporada != null)
				return false;
		} else if (!temporada.equals(other.temporada))
			return false;
		return true;
	}
	
}