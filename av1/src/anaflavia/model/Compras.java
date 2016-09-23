package anaflavia.model;

import java.sql.Date;

public class Compras {
	private Integer id_compra;
	private Integer id_cliente;
	private Integer id_tipo;
	private Double preco;
	private int quantidade;
	private Date data;
	private Tipo_compra tipo = new Tipo_compra();
	private Clientes cliente = new Clientes();
	
	public void setCliente (Clientes cliente){
		this.cliente = cliente;
	}
	public Clientes getCliente(){
		return this.cliente;
	}
	public void setTipo(Tipo_compra tipo){
		this.tipo = tipo;
	}
	public Tipo_compra getTipo(){
		return this.tipo;
	}
	
	public Integer getId_compra() {
		return id_compra;
	}
	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Integer id_tipo) {
		//this.tipo.setId_tipo(id_tipo);
		this.id_tipo = id_tipo;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_compra == null) ? 0 : id_compra.hashCode());
		result = prime * result + ((id_tipo == null) ? 0 : id_tipo.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + quantidade;
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
		Compras other = (Compras) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_compra == null) {
			if (other.id_compra != null)
				return false;
		} else if (!id_compra.equals(other.id_compra))
			return false;
		if (id_tipo == null) {
			if (other.id_tipo != null)
				return false;
		} else if (!id_tipo.equals(other.id_tipo))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Compras [id_compra=" + id_compra +", "+  cliente
				+ ", "+ tipo + ", preco=" + preco + ", quantidade="
				+ quantidade + ", data=" + data + "]";
	}
	
	
	
	
}
