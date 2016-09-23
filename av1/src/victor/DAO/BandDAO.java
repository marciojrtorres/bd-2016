package victor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import victor.models.Album;
import victor.models.Band;
import victor.models.Genre;

public class BandDAO {
	
	private ConnectionFactory cf = new ConnectionFactory();
	private static final int LIMIT = 2;

	public void insert(Band band) {
		Connection con = cf.getConnection();
		
		String sql = "INSERT INTO bands VALUES (DEFAULT, ?, ?, ?, ?);";
		
		try {
			PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, band.getName());
			cmd.setInt(2, band.getBeggin());
			cmd.setObject(3, band.getEnd_band());
			cmd.setString(4, band.getNacionality());
			
			cmd.execute();
			
			ResultSet rs = cmd.getGeneratedKeys();
			if (rs.next())
				band.setId_band(rs.getInt(1));
			
			ArrayList<Album> albums = band.getAlbums();
			
			sql = "INSERT INTO albums VALUES(DEFAULT, ?, ?, ?, ?);";
			
			for (Album alb : albums) {
				
				cmd = con.prepareStatement(sql);
								
				cmd.setString(1, alb.getName());
				cmd.setInt(2, alb.getDuration());
				cmd.setDate(3, alb.getRelease());
				cmd.setInt(4, band.getId_band());
				
				cmd.execute();
			}
			
			ArrayList<Genre> genres = band.getGenres();
			
			sql = "INSERT INTO bands_genres VALUES (?, CAST(? AS genres));";

			
			for (Genre g : genres) {
				cmd = con.prepareStatement(sql);
				
				cmd.setInt(1, band.getId_band());
				cmd.setString(2, g.toString());
				
				cmd.execute();
			}
			
			cmd.close();
			con.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Band selectBand(int id){
		Connection con = cf.getConnection();
		String sql = "SELECT name, beggin, end_band, nacionality FROM bands WHERE id_band = ?;";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery();
			
			Band band = new Band();
			
			if (rs.next()) {
				band.setId_band(id);
				band.setName(rs.getString("name"));
				band.setBeggin(rs.getInt("beggin"));
				band.setEnd_band(rs.getInt("end_band"));
				band.setNacionality(rs.getString("nacionality"));
				band.setAlbums(selectAlbums(id));
				band.setGenres(selectGenres(id));
			}
			
			return band;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public ArrayList<Album> selectAlbums(int id) {
		Connection con = cf.getConnection();
		String sql = "SELECT id_album, name_album, duration, release FROM albums WHERE id_band = ?;";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			
			ArrayList<Album> albums = new ArrayList<Album>();
			
			while(rs.next()){
				Album alb = new Album();
				alb.setId_album(rs.getInt("id_album"));
				alb.setName(rs.getString("name_album"));
				alb.setDuration(rs.getInt("duration"));
				alb.setRelease(rs.getDate("release"));
				albums.add(alb);
			}
			con.close();
			return albums;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ArrayList<Genre> selectGenres(int id) {
		Connection con = cf.getConnection();
		String sql = "SELECT genre FROM bands_genres WHERE id_band = ?";
		PreparedStatement cmd;
		try {
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			
			ResultSet rs = cmd.executeQuery();
			
			ArrayList<Genre> genres = new ArrayList<Genre>();
			
			while(rs.next()){
				Genre g = Genre.valueOf(rs.getString("genre"));
				genres.add(g);
			}
			
			con.close();
			
			return genres;
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public ArrayList<Band> selectByNacionality(String nacio) {
		Connection con = cf.getConnection();
		
		String sql = "SELECT id_band FROM bands WHERE nacionality = ?;";
		
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, nacio);
			
			ResultSet rs = cmd.executeQuery();
			
			ArrayList<Band> bands = new ArrayList<Band>();
			
			while(rs.next()){
				bands.add(selectBand(rs.getInt("id_band")));
			}
			
			con.close();
			return bands;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public ArrayList<Band> selectPage(int page){
		Connection con = cf.getConnection();
		
		String sql = "SELECT id_band FROM bands ORDER BY id_band DESC LIMIT ? OFFSET ?;";
		
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, LIMIT);
			cmd.setInt(2, ((page - 1) + LIMIT));
			
			ResultSet rs = cmd.executeQuery();
			
			ArrayList<Band> bands = new ArrayList<Band>();
			
			while(rs.next()){
				bands.add(selectBand(rs.getInt("id_band")));
			}
			
			return bands;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean deleteBand(int id){
		Connection con = cf.getConnection();
		
		String sql = "DELETE FROM bands WHERE id_band = ?";
		
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			return cmd.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void update(Band band){
		if (band.getId_band() == null) {
			throw new RuntimeException("Id nulo");
		}else{
			deleteBand(band.getId_band());
			insert(band);
		}
	}
	
}
