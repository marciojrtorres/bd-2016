package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Pokedex;
import model.Pokemon;
import pokemon.Evolução;

public class PokedexDAO {
	
	private static final int LIMIT = 2;
	
	public void insert(Pokedex pokedex){   
		Connection con = null;
    	String sql = "INSERT INTO pokedex (nome) VALUES (?)";

        try{   
        con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
        
            	
            	PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            	cmd.setString(1, pokedex.getNome());
                cmd.execute();
             
                ResultSet key = cmd.getGeneratedKeys();
    			if (key.next()) {
    				pokedex.setId(key.getInt(1));
    			}
    			
    			String sqlPokemons = "INSERT INTO pokemons_capturados VALUES (default, ?, ?, ?, evolução(?));";
    			for (Pokemon pokemon : pokedex.getPokemons()) {
    				PreparedStatement cmdPok = con.prepareStatement(sqlPokemons);
    						
    				cmdPok.setInt(1, pokedex.getId());
    				cmdPok.setInt(2, pokemon.getId());
    				cmdPok.setDate(3, new java.sql.Date(pokemon.getRegistro().getTime().getTime()));
    				cmdPok.setString(4, pokemon.getEv().toString());
    				
    				cmdPok.execute();
    			}
                
                cmd.close();
                con.close();
        }
        catch (Exception e){  
        	throw new RuntimeException(e);
        }
    }
	
	//retorna os pokemons do tipo seleciona
	public List selectByTipo(String tipo){
		List pokemons = new ArrayList();
		Connection con = null;
		int capturados =0;
    	String sql = "SELECT nome FROM base WHERE tipo = ?";

        try{   
        con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
        
        PreparedStatement cmd = con.prepareStatement(sql);

		cmd.setString(1, tipo);
		
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next()){
			pokemons.add(rs.getString("nome"));
		}
		
		return pokemons;
        
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Pokedex> selectPage(int pagina) {
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
			
			String sql = "SELECT * FROM pokedex ORDER BY nome DESC OFFSET ? LIMIT "+ LIMIT;

			
			
			PreparedStatement cmd = con.prepareStatement(sql);
			
			int offset = (pagina - 1)*LIMIT;
			
			cmd.setInt(1, offset);

			ResultSet rs = cmd.executeQuery(); // consulta devolve um resultset
				
			List<Pokedex> pokemons = new ArrayList();
			
			if(rs.next()){
				Pokedex pokedex = new Pokedex();
				pokedex.setId(rs.getInt("id"));
				pokedex.setNome(rs.getString("nome"));
				pokemons.add(pokedex);
			}
			
			return pokemons;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Retorna o numero de pokemons capturados por aquele id
	public int selectByCapturados(int id){
		Connection con = null;
		int capturados =0;
    	String sql = "SELECT count(id_capturados) FROM pokemons_capturados INNER JOIN pokedex ON (id_pokedex = id_usuario) WHERE id_usuario = ?;";

        try{   
        con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
        
        PreparedStatement cmd = con.prepareStatement(sql);

		cmd.setInt(1, id);
		
		ResultSet rs = cmd.executeQuery();
		
		if(rs.next()){
			capturados = rs.getInt("count");
		}
		
        return capturados;
        
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean delete(int id) {
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
			
			String sqlPokemon = "DELETE FROM pokemons_capturados WHERE id_pokedex = ?;";

			PreparedStatement cmdpokemon = con.prepareStatement(sqlPokemon);

			cmdpokemon.setInt(1, id);
			
			cmdpokemon.execute();
		
				String sqlpokedex = "DELETE FROM pokedex WHERE id_usuario = ?;";

				PreparedStatement cmdpokedex = con.prepareStatement(sqlpokedex);

				cmdpokedex.setInt(1, id);
				
				return cmdpokedex.execute();
				
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public void update (Pokedex pokedex){
		if(pokedex.getId() == null){
			throw new RuntimeException("O id eh nulo");
		}
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:postgresql://localhost/pokemon", "postgres", "Thaylles");
			
			String sql = "UPDATE pokedex SET nome=? WHERE id_usuario =?;";

			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setString(1, pokedex.getNome());
			cmd.setInt(2, pokedex.getId());
			cmd.execute();
			
			sql = "DELETE FROM pokemons_capturados WHERE id_pokedex= ?";
			
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, pokedex.getId());
			cmd.execute();
			
			String sqlPokemon = "INSERT INTO pokemons_capturados VALUES (default, ?, ?, ?, evolução(?))";
					for (Pokemon pokemon : pokedex.getPokemons()) {
	    				PreparedStatement cmdPok = con.prepareStatement(sqlPokemon);
	    						
	    				cmdPok.setInt(1, pokedex.getId());
	    				cmdPok.setInt(2, pokemon.getId());
	    				cmdPok.setDate(3, new java.sql.Date(pokemon.getRegistro().getTime().getTime()));
	    				cmdPok.setString(4, pokemon.getEv().toString());
	    				cmdPok.execute();
	    			}
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
