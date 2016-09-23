package beatriz.model;

import java.util.ArrayList;
import java.util.List;
public class Escritor {
	private Integer id_escritor;
	private String nome;
	private String sobrenome;
	private Integer cep;

	// gerar getters/setters, toString, hashCode and equals
	public Integer getId() {
		return id_escritor;
	}

	public void setId(Integer id) {
		this.id_escritor = id;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
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

	@Override
	public String toString() {
		return "Escritor [id_escritor=" + id_escritor + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cep=" + cep
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((id_escritor == null) ? 0 : id_escritor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
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
		Escritor other = (Escritor) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (id_escritor == null) {
			if (other.id_escritor != null)
				return false;
		} else if (!id_escritor.equals(other.id_escritor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}
	
	

}