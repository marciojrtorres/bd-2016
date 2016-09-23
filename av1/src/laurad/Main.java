package laurad;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import laurad.dao.InicializaDAO;
import laurad.dao.JogoDAO;
import laurad.dao.UsuarioDAO;
import laurad.model.Genero;
import laurad.model.Jogo;
import laurad.model.Usuario;

public class Main {

	public static void main(String[] args) {

		JogoDAO jogoDAO = new JogoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		InicializaDAO dao = new InicializaDAO();
		dao.deleteAll();
		//dao.insertStandard();
		
		// Inserts
		// /////////////////////////////////////////////////////////////////////////
		// Inserts - jogos
		System.out.println("INSERTS - jogos");
		Jogo j1 = new Jogo();
		j1.setNome("Until Dawn");
		jogoDAO.insertJogo(j1);
		Jogo j2 = new Jogo();
		j2.setNome("Harvest Moon: A Wonderful Life");
		jogoDAO.insertJogo(j2);
		System.out.println("---------------------");
		
		
		// Inserts - usuarios
		System.out.println("INSERTS - usuarios");
		//Date data1 = Date.valueOf("1999-02-05");
		Usuario u1 = new Usuario();
		u1.setNome("Helena");
		u1.setSobrenome("Neves");
		//u1.setDataDeNascimento(data1);
		u1.setGenero(Genero.FEMININO);
		u1.addJogo(j1);
		u1.addJogo(j2);
		usuarioDAO.insertUsuario(u1);
		System.out.println("---------------------");
		//////////////////////////////////////////////////////////////////////////////////
		
		
		// Selects
		// ///////////////////////////////////////////////////////////////////////////////
		// Selects - usuarios
		System.out.println("SELECTS - usuarios");
		// Select de usu�rio por id
		Usuario u2 = usuarioDAO.selectUsuario(u1.getIdUsuario());
		// #1
		System.out.println("#1 " + u2.getNome().equals(u1.getNome()));
		
		// Select de todos os usu�rios
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioDAO.selectUsuarios();
		// #2
		System.out.println("#2 " + usuarios.get(usuarios.size() - 1).getIdUsuario().equals(u1.getIdUsuario()));
		
		// Select por g�nero
		List<Usuario> usuarios2 = usuarioDAO.selectUsuarioByGenero(Genero.FEMININO);
		// #3
		System.out.println("#3 " + (usuarios2.get(usuarios2.size()-1).getGenero() == Genero.FEMININO));
		
		// Select paginado de usu�rios
		// #4
		List<Usuario> usuarios3 = usuarioDAO.selectPageUsuarios(1);
		if (usuarios.size() > UsuarioDAO.getLimit()) System.out.println("#4 " + (usuarios3.size() == UsuarioDAO.getLimit()));
		else System.out.println("#4 " + (usuarios3.size() == usuarios.size()));
		System.out.println("---------------------");
		
		// Selects - jogos
		System.out.println("SELECTS - jogos");		
		// Select de jogo por id
		Jogo j3 = jogoDAO.selectJogo(j1.getIdJogo());
		// #5
		System.out.println("#5 " + j3.getNome().equals(j1.getNome()));
		Jogo j4 = new Jogo();
		j4.setNome("Uncharted 4");
		jogoDAO.insertJogo(j4);
		// #6
		System.out.println("#6 " + jogoDAO.selectJogo(j4.getIdJogo()).getNome().equals(j4.getNome()));
		
		// Select de jogo por nome
		List<Jogo> jogos = jogoDAO.selectJogoByNome("Uncharted 4");
		// #7
		System.out.println("#7 " + (jogos.get(jogos.size()-1).getIdJogo() == j4.getIdJogo()));
		
		// Select de todos os jogos
		List<Jogo> jogos2 = new ArrayList<Jogo>();
		jogos2 = jogoDAO.selectJogos();
		// #8
		System.out.println("#8 " + jogos2.get(jogos2.size() - 1).getNome().equals(j4.getNome()));
		
		// Select paginado de jogos
		List<Jogo> jogos3 = new ArrayList<Jogo>();
		jogos3 = jogoDAO.selectJogosPaginado(1);
		// #9
		System.out.println("#9 " + (jogos3.size() == JogoDAO.getLimit()));
		
		System.out.println("---------------------");
		/////////////////////////////////////////////////////////////////////////////////
		
		
		// Deletes
		// //////////////////////////////////////////////////////////////////////////////
		// DELETES - usuarios
		System.out.println("DELETES - usuarios");
		
		Usuario u3 = new Usuario();
		u3.setNome("Bill");
		u3.setSobrenome("Portoes");
		u3.setGenero(Genero.MASCULINO);
		usuarioDAO.insertUsuario(u3);
		
		// Delete de usu�rio por id
		// O m�todo execute() retorna true se o primeiro resultado for um result set
		// e false se um update ou delete tiver sido realizado. 
		// Como nos interessa o delete o retorno deve ser falso.
		// #10
		System.out.println("#10 " + (usuarioDAO.deleteUsuario(u3.getIdUsuario()) == false));
		usuarioDAO.insertUsuario(u3);
		
		// Delete por usu�rio
		// #11
		System.out.println("#11 " + (usuarioDAO.deleteUsuario(u3) == false));
		System.out.println("---------------------");
		
		// DELETES - jogos
		System.out.println("DELETES - jogos");
		
		Jogo j5 = new Jogo();
		j5.setNome("The Last Of Us");
		jogoDAO.insertJogo(j5);
		
		// Delete de jogo por id
		// #12
		System.out.println("#12 " + (jogoDAO.deleteJogo(j5.getIdJogo()) == false));
		jogoDAO.insertJogo(j5);
		
		// Delete por jogo
		// #13
		System.out.println("#13 " + (jogoDAO.deleteJogo(j5) == false));
		jogoDAO.insertJogo(j5);
		System.out.println("---------------------");
		////////////////////////////////////////////////////////////////////////////////
		
		// Updates
		// /////////////////////////////////////////////////////////////////////////////
		// UPDATES - usuarios
		System.out.println("UPDATES - usuarios");		
		Usuario u4 = new Usuario();
		u4.setNome("Steve");
		u4.setSobrenome("Empregos");
		u4.setGenero(Genero.MASCULINO);
		Date data2 = Date.valueOf("1955-02-24");
		u4.setDataDeNascimento(data2);
		u4.addJogo(j1);
		u4.addJogo(j5);
		usuarioDAO.insertUsuario(u4);
		// #14
		System.out.println("#14 " + usuarioDAO.selectUsuario(u4.getIdUsuario()).equals(u4));
		u4.setSobrenome("Jobs");
		u4.removeJogo(j1);
		usuarioDAO.updateUsuario(u4);
		// #15
		System.out.println("#15 " + usuarioDAO.selectUsuario(u4.getIdUsuario()).getSobrenome().equals("Jobs"));
		// #16
		System.out.println("#16 " + (usuarioDAO.selectUsuario(u4.getIdUsuario()).getJogos().size() == u4.getJogos().size()));
		System.out.println("---------------------");
		
		// UPDATES - jogos
		System.out.println("UPDATES - jogos");
		Jogo j6 = new Jogo();
		j6.setNome("League of Legends");
		jogoDAO.insertJogo(j6);
		// #17
		System.out.println("#17 " + jogoDAO.selectJogo(j6.getIdJogo()).equals(j6));
		j6.setNome("LoL");
		jogoDAO.updateJogo(j6);
		// #18
		System.out.println("#18 " + jogoDAO.selectJogo(j6.getIdJogo()).getNome().equals("LoL"));
		///////////////////////////////////////////////////////////////////////////////////
	
	}

}
