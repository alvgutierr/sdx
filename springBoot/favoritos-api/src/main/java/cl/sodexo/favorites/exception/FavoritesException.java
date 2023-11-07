package cl.sodexo.favorites.exception;

import java.io.Serializable;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
public class FavoritesException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -395344241884360345L;
	private String codeFavoritosException;
	
	public FavoritesException(){
		super();
	}

	public FavoritesException(String message){
		super(message);
	}
	
	public FavoritesException(String code, String message){
		super(message);
		this.codeFavoritosException = code;
	}
	
	public FavoritesException(String message, Throwable cause){
		super(message, cause);
	}
	
	public FavoritesException(Throwable cause){
		super(cause);
	}

	public String getCodeFavoritosException() {
		return codeFavoritosException;
	}

	public void setCodeFavoritosException(String code) {
		this.codeFavoritosException = code;
	}

	

}
