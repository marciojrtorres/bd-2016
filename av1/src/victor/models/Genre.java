package victor.models;

public enum Genre {
	ROCK_PSICODELICO("rock psicodelico"), ROCK_ALTERNATIVO("rock alternativo"), GRUNGE("grunge"), ROCK("rock"),
	ROCK_PROGRESSIVO("rock progressivo");
	
	private String genre;
	
	Genre(String valor){
		genre = valor;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public void setGenre(String str){
		genre = str;
	}
}