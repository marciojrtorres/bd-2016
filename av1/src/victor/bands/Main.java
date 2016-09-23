package victor.bands;

import java.sql.Date;
import java.util.ArrayList;

import victor.DAO.BandDAO;
import victor.models.Album;
import victor.models.Band;
import victor.models.Genre;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		BandDAO band_DAO = new BandDAO();
		
		Band band1 = new Band();
		band1.setName("Dire Straits");
		band1.setBeggin(1977);
		band1.setEnd_band(1995);
		band1.setNacionality("United Kingdom");
		
		Album album1 = new Album();
		album1.setName("Brothers in Arms");
		album1.setDuration(54);
		album1.setRelease(new Date(1985 - 1900, 5 - 1, 13));
		
		Album album2 = new Album();
		album2.setName("Making Movies");
		album2.setDuration(42);
		album2.setRelease(new Date(1979 - 1900, 4 - 1, 15));
		
		band1.addAlbum(album1);
		band1.addAlbum(album2);
		
		band1.addGenre(Genre.ROCK);
		band1.addGenre(Genre.ROCK_PROGRESSIVO);
		
		band_DAO.insert(band1);
		
		Band band_result = band_DAO.selectBand(band1.getId_band());
		
		System.out.println(band_result.equals(band1));
		
		Band band2 = new Band();
		band2.setName("Nirvana");
		band2.setBeggin(1987);
		band2.setEnd_band(1994);
		band2.setNacionality("USA");
		
		Band band3 = new Band();
		band3.setName("Rolling Stones");
		band3.setBeggin(1962);
		band3.setNacionality("United Kingdom");
		
		band_DAO.insert(band2);
		band_DAO.insert(band3);
		
		ArrayList<Band> bands_nacionality = band_DAO.selectByNacionality("United Kingdom");
		
		System.out.println(bands_nacionality.size() == 2);
		System.out.println(bands_nacionality.get(0).getName().equals("Dire Straits"));
		System.out.println(bands_nacionality.get(1).getName().equals("Rolling Stones"));
		
		ArrayList<Band> bands_offset = band_DAO.selectPage(1);
		
		band_DAO.deleteBand(band3.getId_band());
		
		bands_nacionality = band_DAO.selectByNacionality("United Kingdom");
		System.out.println(bands_nacionality.size() == 1);
		
		band2.setName("Foo Fighters");
		
		band_DAO.update(band2);
		
		System.out.println(band_DAO.selectBand(band2.getId_band()).equals(band2));
	}

}
