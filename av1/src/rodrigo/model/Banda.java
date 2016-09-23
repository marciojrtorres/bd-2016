package rodrigo.model;

import java.sql.Date;


public class Banda {
	
		public enum NAC {	
			EST, BR;
		}
	
	private String nome;
	private Integer qtde_albums;
	private String genero;
	private NAC nacionalidade;
	private Integer id;
	
	public Banda(){
	
	}
	public Banda(String nome,Integer qtde_albums, Integer id){
		this.nome = nome;
		this.qtde_albums = qtde_albums;
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
		Banda other = (Banda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Banda [nome=" + nome + ", qtde_albums=" + qtde_albums
				+ ", genero=" + genero + ", nacionalidade=" + nacionalidade
				+ ", id=" + id + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNacionalidade(NAC nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Integer getqtde_albums() {
		return qtde_albums;
	}
	public void setqtde_albums(Integer qtde_albums) {
		this.qtde_albums = qtde_albums;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public NAC getNacionalidade() {
		return nacionalidade;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public void setNacionalidade(String string) {
		if(string =="BR")this.nacionalidade = NAC.BR;
		if(string=="EST")this.nacionalidade = NAC.EST;
		
	}
}