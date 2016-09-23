package thaylles.model;

import java.util.Calendar;

import thaylles.pokemon.Evolucao;


public class Pokemon {
	private Integer id;
	private Calendar registro;
	private Evolucao ev;
	
	public Evolucao getEv() {
		return ev;
	}
	public void setEv(Evolucao ev) {
		this.ev = ev;
	}
	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", registro=" + registro + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ev == null) ? 0 : ev.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
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
		Pokemon other = (Pokemon) obj;
		if (ev != other.ev)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Calendar getRegistro() {
		return registro;
	}
	public void setRegistro(Calendar registro) {
		this.registro = registro;
	}
	
	
}
