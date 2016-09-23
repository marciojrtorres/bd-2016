package jeniffer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class Aluno {
	private Integer id;
	private String nome;
	private Genero genero;
	private Date aniversario;
	private List<Serie> series = new ArrayList();
	
	
	public Genero getGenero() {
		return genero;
	}
	public void addSerie(Serie serie) {
		series.add(serie);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Serie> getSeries() {
		return series;
	}
	
	public void addTelefone(Serie serie) {
		series.add(serie);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aniversario == null) ? 0 : aniversario.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
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
		Aluno other = (Aluno) obj;
		if (aniversario == null) {
			if (other.aniversario != null)
				return false;
		} else if (!aniversario.equals(other.aniversario))
			return false;
		if (genero != other.genero)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
		} else if (!series.equals(other.series))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", genero=" + genero + ", aniversario=" + aniversario
				+ ", series=" + series + "]";
	}
	public String getNome() {
		return nome;
	}
	public Date getAniversario() {
		return aniversario;
	}
	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setGenero(Genero f) {
		// TODO Auto-generated method stub
		
	}

}
