package vinicius.model;

public class Cliente {
	
	private Integer codigo;
	private String nome;
	private String sobrenome;
	private String cidade;
	private String uf;
	private String cep;
	
	public Cliente(){
		
	};
	public Cliente(String nome, String sobrenome, String cidade, String uf, String cep) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}
	public void setCodigo(Integer codigo){
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cidade=" + cidade
				+ ", uf=" + uf + ", cep=" + cep + "]";
	}
	
}
