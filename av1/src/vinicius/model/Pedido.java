package vinicius.model;

import java.util.*;


import java.util.Date;

import vinicius.Status_ped;

public class Pedido {
	
	private Integer codigo;
	private Integer cod_vend;
	private Integer cod_cli;
	private Date    data_pedido;
	private Date    data_entrega;
	private Double  frete;
	private Status_ped status;
	private List<Item> itens = new ArrayList();
	
	
	public Pedido(){
		
	};	
	public Pedido(Integer codigo, Integer cod_vend, Integer cod_cli, Date data_pedido, Date data_entrega, Double frete,
			Status_ped status, List<Item> itens) {
		super();
		this.codigo = codigo;
		this.cod_vend = cod_vend;
		this.cod_cli = cod_cli;
		this.data_pedido = data_pedido;
		this.data_entrega = data_entrega;
		this.frete = frete;
		this.status = status;
		this.itens = itens;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public void addItem(Item item){
		itens.add(item);
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public Integer getCod_vend() {
		return cod_vend;
	}
	public void setCod_vend(Integer cod_vend) {
		this.cod_vend = cod_vend;
	}
	public Integer getCod_cli() {
		return cod_cli;
	}
	public void setCod_cli(Integer cod_cli) {
		this.cod_cli = cod_cli;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	public Date getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}
	public Double getFrete() {
		return frete;
	}
	public void setFrete(Double frete) {
		this.frete = frete;
	}
	public Status_ped getStatus() {
		return status;
	}
	public void setStatus(Status_ped status) {
		this.status = status;
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
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cod_vend=" + cod_vend + ", cod_cli=" + cod_cli + ", data_pedido="
				+ data_pedido + ", data_entrega=" + data_entrega + ", frete=" + frete + ", status=" + status + "]";
	}
	
	
}
