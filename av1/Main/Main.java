package Main;

import java.util.Calendar;
import java.util.List;

import DAO.PokedexDAO;
import model.*;
import pokemon.Evolução;

public class Main {

	public static void main(String[] args){
		
		
		System.out.println("----------TESTE 1----------");
		//Inserir 2 pokemons em 3 pokedex.
		
		Pokedex Thaylles = new Pokedex();
		Thaylles.setNome("Thaylles");
		
		Pokemon pokemon1 = new Pokemon();
		pokemon1.setId(1);
		pokemon1.setEv(Evolução.Inicial);
		pokemon1.setRegistro(Calendar.getInstance());
		Thaylles.addPokemons(pokemon1);
		
		Pokemon pokemon2 = new Pokemon();
		pokemon2.setId(2);
		pokemon2.setRegistro(Calendar.getInstance());
		Thaylles.addPokemons(pokemon2);
		
		Pokedex Joao = new Pokedex();
		Joao.setNome("Joao");
		
		Pokemon pokemon3 = new Pokemon();
		pokemon3.setId(3);
		pokemon3.setEv(Evolução.Final);
		pokemon3.setRegistro(Calendar.getInstance());
		Joao.addPokemons(pokemon3);
		
		Pokemon pokemon4 = new Pokemon();
		pokemon4.setId(37);
		pokemon4.setEv(Evolução.Inicial);
		pokemon4.setRegistro(Calendar.getInstance());
		Joao.addPokemons(pokemon4);
		
		Pokedex Marcio = new Pokedex();
		Marcio.setNome("Marcio");
		
		Pokemon pokemon5 = new Pokemon();
		pokemon5.setId(150);
		pokemon5.setRegistro(Calendar.getInstance());
		Marcio.addPokemons(pokemon5);
		
		Pokemon pokemon6 = new Pokemon();
		pokemon6.setId(151);
		pokemon6.setRegistro(Calendar.getInstance());
		Marcio.addPokemons(pokemon6);
		
		
		PokedexDAO dao = new PokedexDAO();
		dao.insert(Thaylles);
		dao.insert(Joao);
		dao.insert(Marcio);
		
		
	
		System.out.println();
		System.out.println("--------TESTE 2---------");
		//Testando as selects
		
		int capturados = dao.selectByCapturados(1);
		System.out.println(capturados);
		
		List aqui = dao.selectByTipo("Grass");
		System.out.println(aqui);
		
		List<Pokedex> pag1 = dao.selectPage(1);
		System.out.println(pag1);
		
		
		System.out.println();
		System.out.println("------TESTE 3-------");
		//Delete e update
		
		Thaylles.setNome("Rosa");
		dao.update(Thaylles);
		System.out.println(Thaylles.getNome() == "Rosa");
		
		dao.delete(1);
		
	}

}
