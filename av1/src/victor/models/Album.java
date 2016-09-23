package victor.models;

import java.sql.Date;

public class Album {

	private Integer id_album;
	private String	name;
	private Integer duration;
	private Date	release;
	
	public Album(){
		
	}

	public Integer getId_album() {
		return id_album;
	}

	public void setId_album(Integer id_album) {
		this.id_album = id_album;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	@Override
	public String toString() {
		return "Album [id_album=" + id_album + ", name=" + name + ", duration="
				+ duration + ", release=" + release + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((id_album == null) ? 0 : id_album.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((release == null) ? 0 : release.hashCode());
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
		Album other = (Album) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id_album == null) {
			if (other.id_album != null)
				return false;
		} else if (!id_album.equals(other.id_album))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (release == null) {
			if (other.release != null)
				return false;
		} else if (!release.equals(other.release))
			return false;
		return true;
	}
	
}
