package rodrigo.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
public class Gravadora {
	private Integer id;
	private String nome;
	private Integer qtde_bandas;
	private Date data;
	private List<Banda> bandas = new ArrayList();
	
	
	public void addBanda(Banda b){
		bandas.add(b);
	}
	public void setBandas(List<Banda> bandas) {
		this.bandas = bandas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQtde_bandas() {
		return qtde_bandas;
	}
	public void setQtde_bandas(Integer qtde_bandas) {
		this.qtde_bandas = qtde_bandas;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Banda [id=" + id + ", nome=" + nome + ", qtde_bandas="
				+ qtde_bandas + ", data=" + data + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	public List<Banda> getBandas() {
		return bandas;
	}
	
	
}
