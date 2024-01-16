package co.edu.unbosque.util.exception;

public class UserException extends Exception{
	
	public int error;

	public UserException(int error) {
		super();
		this.error = error;
	}
	
	@Override
	public String getMessage() {
		switch (error) {
		case 0:return "Indice invalido.";
		case 1:return "Nombre invalido. Ingrese solo letras.";
		case 2:return "Tipo de dcumento invalido. Ingrese solo letras.";
		}
		return "Usuario invalido.";
	}

	

}
