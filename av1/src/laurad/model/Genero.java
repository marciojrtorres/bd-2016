package laurad.model;

public enum Genero {

	MASCULINO, FEMININO;

	public static Genero fromInteger(int i) {
		switch (i) {
		case 0:
			return MASCULINO;
		case 1:
			return FEMININO;
		}
		return null;
	}

	public static Integer toInteger(Genero genero) {
		switch (genero) {
		case MASCULINO:
			return 0;
		case FEMININO:
			return 1;
		}
		return null;
	}

}
