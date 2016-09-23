package artur.model;

import java.util.*;

public class Pessoa {
	private String nome;
	private Date nascimento;
	private Sexo sexo;
	private String endereco;
	private String telefone;
	private Integer id;
	private String email;

	public Pessoa() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(String string) {
		switch (string) {
			case "feminino":
			case "Feminino":
				sexo = Sexo.FEMININO;
			break;
			
			case "Masculino":
			case "masculino":
				sexo = Sexo.MASCULINO;
			break;
			
			default:
				
			break;
		}
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
	
	public String toString () {
		return nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}