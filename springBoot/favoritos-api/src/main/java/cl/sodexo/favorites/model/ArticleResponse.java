package cl.sodexo.favorites.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@Builder
@Data
@EqualsAndHashCode(of = "id")
public class ArticleResponse {

	@Schema(description = "Id interno del favorito", name = "", required = true, defaultValue = "0")
	private Integer id;
	
	@Schema(description = "Id de la noticia", name = "", required = true, defaultValue = "0")
	private Integer articleId;
	
	@Schema(description = "Titulo de la noticia", name = "", required = true, defaultValue = "Titulo")
	private String articleTitle;
	
	@Schema(description = "Datos de la noticia", name = "", required = true, defaultValue = "{}")
	private String dataArticle;
	
	@Schema(description = "Fecha creacion favorito", name = "", required = true, defaultValue = "0000-00-00")
	private LocalDateTime favoriteCreateDatetime;
}
