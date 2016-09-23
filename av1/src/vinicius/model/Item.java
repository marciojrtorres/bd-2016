package vinicius.model;

public class Item{
	
	private Integer cod_ped;
	private Produto produto;
	private Integer quantidade;
	
	public Item(){
		
	};	
	public Item(Integer cod_ped, Produto produto, Integer quantidade) {
		super();
		this.cod_ped = cod_ped;
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Integer getCod_ped() {
		return cod_ped;
	}
	public void setCod_ped(Integer cod_ped) {
		this.cod_ped = cod_ped;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return "Item [cod_ped=" + cod_ped + ", produto=" + produto + ", quantidade=" + quantidade + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_ped == null) ? 0 : cod_ped.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		Item other = (Item) obj;
		if (cod_ped == null) {
			if (other.cod_ped != null)
				return false;
		} else if (!cod_ped.equals(other.cod_ped))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
}
