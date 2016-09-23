package anaflavia.model;

public class Tipo_compra {
	private Integer id_tipo;
	private String tipo;
	
	public Integer getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Integer id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_tipo == null) ? 0 : id_tipo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Tipo_compra other = (Tipo_compra) obj;
		if (id_tipo == null) {
			if (other.id_tipo != null)
				return false;
		} else if (!id_tipo.equals(other.id_tipo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tipo_compra [id_tipo=" + id_tipo + ", tipo=" + tipo + "]";
	}
	
	

}
