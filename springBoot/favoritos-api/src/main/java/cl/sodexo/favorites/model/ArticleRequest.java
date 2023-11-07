package cl.sodexo.favorites.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ArticleRequest {

	@Positive(message = "Id debe ser mayor a 0")
	@Schema(description = "Id de la noticia", name = "", required = true, defaultValue = "0")
	//@Pattern(message = "Id debe ser numero", regexp = "^\\d{10}$")
	@JsonProperty("id")
	@Valid
	private Integer id;//: 21373,
    
	@NotBlank(message = "Title no puede ser vacio o nulo")
	@Schema(description = "titulo de la noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("title")
	@Valid
	private String title;// "How long will Jeff Bezos continue to subsidize his New Shepard rocket?",
	
	@NotNull(message = "Url no puede ser nulo")
	@Schema(description = "url de la noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("url")
	@Valid
	private String url;// "https://arstechnica.com/space/2023/11/as-virgin-galactic-soars-blue-origins-new-shepard-remains-grounded/",
	
	@NotNull(message = "Imagen no puede ser nulo")
	@Schema(description = "Imagen de la noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("image_url")
	@Valid
	private String imageUrl;// "https://cdn.arstechnica.net/wp-content/uploads/2021/10/GettyImages-1329731025-1.jpg",
	
	//@NotNull(message = "Site no puede ser nulo")
	@Schema(description = "Site de la noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("news_site")
	@Valid
	private String newsSite; // "Arstechnica",
	
	@NotNull(message = "Resumen noticia no puede ser nulo")
	@Schema(description = "Resumen noticia ", name = "", required = true, defaultValue = "0")
	@JsonProperty("summary")
	@Valid
	private String summary; //"\"It's definitely a money loser. Always has been.\"",
	
	@NotNull(message = "Fecha publicacion no puede ser nulo")
	@Schema(description = "Fecha publicacion noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("published_at")
	@Valid
	private String publishedAt;// "2023-11-03T16:06:27Z",
	
	//@NotNull(message = "Fecha actualizacion no puede ser nulo")
	@Schema(description = "Fecha actualizacion noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("updated_at")
	@Valid
	private String updatedAt; //"2023-11-03T16:24:25.809000Z",
	
	@NotNull(message = "Presentacion no puede ser nulo")
	@Schema(description = "Presentacion de la noticia", name = "", required = true, defaultValue = "0")
	@JsonProperty("featured")
	@Valid
	private String featured; //false,
	
	@NotNull(message = "Lanzamiento noticia no puede ser nulo")
	@Schema(description = "Lanzamiento de la noticia", name = "", required = true, defaultValue = "[]")
	@JsonProperty("launches")
	@Valid
	//@Schema(name = "launches", type = "array", example = "[\"str1\", \"str2\", \"str3\"]")
	private List<String> launches; //[],
	
	@NotNull(message = "Eventos no puede ser nulo")
	@Schema(description = "Eventos de la noticia", name = "", required = true, defaultValue = "[]")
	@JsonProperty("events")
	@Valid
	//@Schema(name = "launches", type = "array", example = "[\"str1\", \"str2\", \"str3\"]")
	private List<String> events; //[]
    
}
