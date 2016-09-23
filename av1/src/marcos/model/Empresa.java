package marcos.model;

public class Empresa {
 Integer idEmpresa;
 String cnpj;
 String nomeEmpresa;

public Integer getIdEmpresa() {
	return idEmpresa;
}
public void setIdEmpresa(Integer idEmpresa) {
	this.idEmpresa = idEmpresa;
}
public String getCnpj() {
	return cnpj;
}
public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}
public String getNomeEmpresa() {
	return nomeEmpresa;
}
public void setNomeEmpresa(String nomeEmpresa) {
	this.nomeEmpresa = nomeEmpresa;
}
@Override
public String toString() {
	return "Empresa [idEmpresa=" + idEmpresa + ", cnpj=" + cnpj + ", nomeEmpresa=" + nomeEmpresa + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
	result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
	result = prime * result + ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
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
	Empresa other = (Empresa) obj;
	if (cnpj == null) {
		if (other.cnpj != null)
			return false;
	} else if (!cnpj.equals(other.cnpj))
		return false;
	if (idEmpresa == null) {
		if (other.idEmpresa != null)
			return false;
	} else if (!idEmpresa.equals(other.idEmpresa))
		return false;
	if (nomeEmpresa == null) {
		if (other.nomeEmpresa != null)
			return false;
	} else if (!nomeEmpresa.equals(other.nomeEmpresa))
		return false;
	return true;
}

}
