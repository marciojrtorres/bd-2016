package rai.model;

public enum Tipo {
	Solo("Solo"),Grupo("Grupo");
	private String nome;
	Tipo(String nome){
		this.nome=nome;
	}
	public String getNome(){
        return this.nome;
    }
	public void setTipo(String nome){
		this.nome=nome;
	}

}
