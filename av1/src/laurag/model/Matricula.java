package laurag.model;

import java.sql.Date;

public class Matricula {

	private int id_aluno;
	private int id_disciplina;
	private Date inscricao;

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public int getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(int id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public Date getInscricao() {
		return inscricao;
	}

	public void setInscricao(Date inscricao) {
		this.inscricao = inscricao;
	}

	public String toString() {
		return "Matricula [id_aluno=" + id_aluno + ", id_disciplina=" + id_disciplina + ", inscricao=" + inscricao
				+ "]";
	}
}