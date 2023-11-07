package cl.sodexo.favorites.controller.error;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ApiError implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 922578052482096553L;

	@JsonProperty("status")
	@Schema(description = "Código de estado HTTP aplicable a este problema.", name = "status", required = false, defaultValue = "422")
	private String status;

	@JsonProperty("title")
	@Schema(description = "Resumen breve y legible del problema.", name = "title", required = false, defaultValue = "data invalida")
	private String title;
	
	@JsonProperty("errors")
	@Schema(description = "List de errores", name = "errors", required = false, defaultValue = "")
	private List<String> errors;
	
	/*
	@JsonProperty("errors")
	@Schema(description = "", name = "errors", required = false, defaultValue = "")
	private List<Error> errors;
*/
}
