package eduarda.model;

import java.sql.Date;

public class Matricula {

	private Integer id_aluno;
	private Integer id_curso;
	private Date data_matricula;
	
	
	public Integer getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(Integer id_aluno) {
		this.id_aluno = id_aluno;
	}
	public Integer getId_curso() {
		return id_curso;
	}
	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}
	public Date getData_matricula() {
		return data_matricula;
	}
	public void setData_matricula(Date data_matricula) {
		this.data_matricula = data_matricula;
	}
	@Override
	public String toString() {
		return "Matricula [id_aluno=" + id_aluno + ", id_curso=" + id_curso + ", data_matricula=" + data_matricula
				+ "]";
	}
	public Matricula(Integer id_aluno, Integer id_curso, Date data_matricula) {
		super();
		this.id_aluno = id_aluno;
		this.id_curso = id_curso;
		this.data_matricula = data_matricula;
	}
	
	
	public Matricula(){
		
	}
	

}
