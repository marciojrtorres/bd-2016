package jeniffer.controlador;

import java.sql.Date;
import java.sql.SQLException;

import jeniffer.dao.VicioDAO;
import jeniffer.model.Aluno;
import jeniffer.model.Genero;
import jeniffer.model.Serie;

public class Main {

	public static void main(String[] args) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setNome("Mary");
		aluno.setGenero(Genero.F);
		Date data = Date.valueOf("1870-08-10");
		aluno.setAniversario(data);
		
		Serie serie = new Serie();
		serie.setNome("Stranger Things");
		serie.setTemporada(1);
		serie.setEpisodio(1);
		serie.setEpisodio(2);
		
		aluno.addSerie(serie);
		
		VicioDAO dao = new VicioDAO();
		dao.insert(aluno);
		System.out.println(aluno);
		
		
	}

}
