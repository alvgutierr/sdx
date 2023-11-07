package cl.sodexo.favorites.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cl.sodexo.favorites.controller.error.ApiError;
import cl.sodexo.favorites.exception.FavoritesException;
import lombok.extern.slf4j.Slf4j;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionControllerAdvice {

	private static final String httpStatus = String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value());
	private static final String argumentNotValid = "Argument Not Valid";
	private static final String errorFavoritesServices = "Error Favorites Services";
	private static final String errorServices = "Error Services";
	private static final String fieldSeparator = " -- ";

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handlerException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		
		log.error("1 Exception:", ex);
		final List<String> errors = new ArrayList<>();		
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String s = fieldError.getField().concat(fieldSeparator).concat(fieldError.getDefaultMessage()).concat(fieldSeparator).concat(ex.getMessage());
			errors.add(s);
		}
		return ResponseEntity.unprocessableEntity().body(ApiError.builder().status(httpStatus).title(argumentNotValid).errors(errors).build());
	}

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(FavoritesException.class)
	public ResponseEntity<ApiError> handlerException(FavoritesException ex, HttpServletRequest request) {
		
		log.error("2 Exception:", ex);
		final List<String> errors = new ArrayList<>();		
		//errors.add(new Error(ex.getCodeFavoritosException().concat(fieldSeparator).concat(ex.getMessage()),ex));
		errors.add(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(ApiError.builder().status(httpStatus).title(errorFavoritesServices).errors(errors).build());
	}

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handlerException(Exception e, HttpServletRequest request) {
		
		log.error("3 Exception:", e);
		//final List<Error> errors = new ArrayList<>();
		final List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		return ResponseEntity.unprocessableEntity().body(ApiError.builder().status(httpStatus).title(errorServices).errors(errors).build());
	}
}
