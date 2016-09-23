package victor.models;

import java.util.ArrayList;

public class Band {
	
	private Integer id_band;
	private String  name;
	private Integer beggin;
	private Integer end_band;
	private String  nacionality;
	private ArrayList<Album> albums = new ArrayList<Album>();
	private ArrayList<Genre> genres = new ArrayList<Genre>();
	
	public Band(){
	}
	
	public void addAlbum(Album alb){
		albums.add(alb);
	}
	
	public void addGenre(Genre g){
		genres.add(g);
	}
	
	public Integer getId_band() {
		return id_band;
	}
	public void setId_band(Integer id_band) {
		this.id_band = id_band;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBeggin() {
		return beggin;
	}
	public void setBeggin(Integer beggin) {
		this.beggin = beggin;
	}
	public Integer getEnd_band() {
		return end_band;
	}
	public void setEnd_band(Integer end_band) {
		this.end_band = end_band;
	}
	public String getNacionality() {
		return nacionality;
	}
	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Band [id_band=" + id_band + ", name=" + name + ", beggin="
				+ beggin + ", end_band=" + end_band + ", nacionality="
				+ nacionality + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beggin == null) ? 0 : beggin.hashCode());
		result = prime * result
				+ ((end_band == null) ? 0 : end_band.hashCode());
		result = prime * result + ((id_band == null) ? 0 : id_band.hashCode());
		result = prime * result
				+ ((nacionality == null) ? 0 : nacionality.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Band other = (Band) obj;
		if (beggin == null) {
			if (other.beggin != null)
				return false;
		} else if (!beggin.equals(other.beggin))
			return false;
		if (end_band == null) {
			if (other.end_band != null)
				return false;
		} else if (!end_band.equals(other.end_band))
			return false;
		if (id_band == null) {
			if (other.id_band != null)
				return false;
		} else if (!id_band.equals(other.id_band))
			return false;
		if (nacionality == null) {
			if (other.nacionality != null)
				return false;
		} else if (!nacionality.equals(other.nacionality))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
