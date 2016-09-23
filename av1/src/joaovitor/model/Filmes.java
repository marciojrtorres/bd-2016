package joaovitor.model;

public class Filmes {
	
	private Integer id_filme;
	private String nome;
	private String genero;
	private Integer qntd;
	private Integer  id_cliente;
	
	public Integer getId_filme() {
		return id_filme;
	}
	public void setId_filme(Integer id_filme) {
		this.id_filme = id_filme;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getQntd() {
		return qntd;
	}
	public void setQntd(Integer qntd) {
		this.qntd = qntd;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_filme == null) ? 0 : id_filme.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filmes other = (Filmes) obj;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_filme == null) {
			if (other.id_filme != null)
				return false;
		} else if (!id_filme.equals(other.id_filme))
			return false;
		return true;
	}
	public String toString() {
		return "Filmes [id_filme=" + id_filme + ", nome=" + nome + ", genero="
				+ genero + ", qntd=" + qntd + ", id_cliente=" + id_cliente
				+ "]";
	}
	
	
	
}
