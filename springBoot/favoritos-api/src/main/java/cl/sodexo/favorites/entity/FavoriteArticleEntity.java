package cl.sodexo.favorites.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "favorites_articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteArticleEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="article_id")
	private Integer articleId;
	
	@Column(name="article_title")
	private String articleTitle;
	
	@Column(name="data_article", columnDefinition = "json")
	//@JsonRawValue
	private String dataArticle;
			
	@Column(name="favorite_create_datetime")
	private LocalDateTime favoriteCreateDatetime;

}

