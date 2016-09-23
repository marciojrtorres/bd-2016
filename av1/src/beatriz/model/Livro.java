package beatriz.model;

import java.sql.Date;

public class Livro {

	private Integer id_escritor;
	private String titulo;
	private Date publicacao;
	private String livro_genero;
	
	/*Genero romance = Genero.ROMANCE;	
	Genero ficcao_cientifica = Genero.FICCAO_CIENTIFICA;	
	Genero terror = Genero.TERROR;*/
	public Integer getId_escritor() {
		return id_escritor;
	}
	public String getLivro_genero() {
		return livro_genero;
	}
	public void setLivro_genero(String livro_genero) {
		this.livro_genero = livro_genero;
	}
	public void setId_escritor(Integer id_escritor) {
		this.id_escritor = id_escritor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_escritor == null) ? 0 : id_escritor.hashCode());
		result = prime * result + ((livro_genero == null) ? 0 : livro_genero.hashCode());
		result = prime * result + ((publicacao == null) ? 0 : publicacao.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Livro other = (Livro) obj;
		if (id_escritor == null) {
			if (other.id_escritor != null)
				return false;
		} else if (!id_escritor.equals(other.id_escritor))
			return false;
		if (livro_genero == null) {
			if (other.livro_genero != null)
				return false;
		} else if (!livro_genero.equals(other.livro_genero))
			return false;
		if (publicacao == null) {
			if (other.publicacao != null)
				return false;
		} else if (!publicacao.equals(other.publicacao))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Livro [id_escritor=" + id_escritor + ", titulo=" + titulo + ", publicacao=" + publicacao
				+ ", livro_genero=" + livro_genero + "]";
	}

	
	
	
}