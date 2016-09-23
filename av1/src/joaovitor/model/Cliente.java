package joaovitor.model;

import java.sql.Date;

public class Cliente {
	
	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private Date data_nasc;
	private PagDia pagdia;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	public PagDia getPagdia() {
		return pagdia;
	}
	public void setPagdia(PagDia pagdia) {
		this.pagdia = pagdia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome
				+ ", email=" + email + ", data_nasc=" + data_nasc + ", pagdia="
				+ pagdia + ", id=" + id + "]";
	}



	
}
	