package erick.model;

public class Produtos {
	
	private Integer id_produto;
	private Integer id;
	private String nome;
	private Integer preco;
	private Enumerado raridade;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_produto() {
		return id_produto;
	}
	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	public Enumerado getRaridade() {
		return raridade;
	}
	public void setRaridade(Enumerado raridade) {
		this.raridade = raridade;
	}
	@Override
	public String toString() {
		if (id_produto == null) {
			return nome + " " + preco + " " + raridade;
		}
		return id_produto + " " + id + " " + nome + " " + preco + " " + raridade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((id_produto == null) ? 0 : id_produto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result
				+ ((raridade == null) ? 0 : raridade.hashCode());
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
		Produtos other = (Produtos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_produto == null) {
			if (other.id_produto != null)
				return false;
		} else if (!id_produto.equals(other.id_produto))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (raridade != other.raridade)
			return false;
		return true;
	}
	
	
	
}
