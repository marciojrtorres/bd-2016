package laurad.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nome;
	private String sobrenome;
	private Date dataDeNascimento;
	private Genero genero;
	private Integer idUsuario;
	private List<Jogo> jogos = new ArrayList<Jogo>();
		
	public Usuario() {
		
	}
	public void addJogo(Jogo jogo) {
			jogos.add(jogo);
	}
	public void removeJogo(Jogo jogo) {
		for (int i = 0; i < jogos.size(); i++) {
			if(jogos.get(i).equals(jogo)) jogos.remove(i);
		}
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
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Jogo> getJogos() {
		return jogos;
	}
	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", sobrenome=" + sobrenome
				+ ", dataDeNascimento=" + dataDeNascimento + ", genero="
				+ genero + ", idUsuario=" + idUsuario + ", jogos=" + jogos
				+ "]";
	}
	
}
