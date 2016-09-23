package vinicius.model;

public class Produto {
	
	private Integer codigo;
	private String descricao;
	private String categoria;
	private Double preco;
	private Integer qtd_estoque;
	
	
	public Produto(){
		
	};
	public Produto(String descricao, String categoria, Double preco, Integer qtd_estoque) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
		this.qtd_estoque = qtd_estoque;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", categoria=" + categoria + ", preco="
				+ preco + ", qtd_estoque=" + qtd_estoque + "]";
	}
	
}
