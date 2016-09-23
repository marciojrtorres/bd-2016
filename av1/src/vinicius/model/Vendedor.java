package vinicius.model;

import java.util.Date;

public class Vendedor {
	
	private Integer codigo;
	private String nome;
	private String sobrenome;
	private Date data_nasc; 
	private String regiao;
	
	public Vendedor(){
		
	};
	public Vendedor(String nome, String sobrenome, Date data_nasc, String regiao) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.data_nasc = data_nasc;
		this.regiao = regiao;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	@Override
	public String toString() {
		return "Vendedor [codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", data_nasc=" + data_nasc
				+ ", regiao=" + regiao + "]";
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
		Vendedor other = (Vendedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
