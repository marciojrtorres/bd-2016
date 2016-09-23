package rai.model;
public class Artista {
	private Integer id;
	private Integer id_empresario;
	private String nome;
	private String data_criacao;
	private Tipo tip;
	public Integer getId_empresario() {
		return id_empresario;
	}
	public void setId_empresario(Integer id_empresario) {
		this.id_empresario = id_empresario;
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
		Artista other = (Artista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Artista [id=" + id + ", id_empresario=" + id_empresario + ", nome=" + nome + ", data_criacao="
				+ data_criacao + ", tip=" + tip + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(String data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Tipo getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.setTip(tip);
	}

	

}

