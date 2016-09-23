package anacarolina.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import anacarolina.dao.CursoDAO;

public class Aluno {
	
	private Integer id_aluno;
	private String matricula;
	private String nome;
	private String sobrenome;
	private Date data_inicio;
	private Integer id_curso;
	
	public void setId_curso(Integer id_curso){
		CursoDAO dao = new CursoDAO();
		List<Integer> ids = dao.selectId();
		for(int i = 0; i < ids.size(); i++){
			if(id_curso.equals(ids.get(i))){
				this.id_curso = id_curso;
			} 
		}
		
		if(!this.id_curso.equals(id_curso))
		System.out.println("Curso inexistente");
		
		
	}
	
	public Integer getId_curso(){
		return id_curso;
	}
	

	
	public Integer getId_aluno() {
		return id_aluno;
	}
	
	public void setId_aluno(Integer id_aluno) {
		this.id_aluno = id_aluno;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	
	public Date getData_inicio() {
		return data_inicio;
	}
	
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}



	@Override
	public String toString() {
		return "Aluno [id_aluno=" + id_aluno + ", matricula=" + matricula + ", nome=" + nome + ", sobrenome="
				+ sobrenome + ", data_inicio=" + data_inicio + ", id_curso=" + id_curso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_aluno == null) ? 0 : id_aluno.hashCode());
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
		if (id_aluno == null) {
			if (other.id_aluno != null)
				return false;
		} else if (!id_aluno.equals(other.id_aluno))
			return false;
		return true;
	}
	
	
	
}
