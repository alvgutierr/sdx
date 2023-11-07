package cl.sodexo.favorites.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sodexo.favorites.entity.FavoriteArticleEntity;


/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
public interface ArticleRepository extends JpaRepository<FavoriteArticleEntity, Integer> {
	
	Optional<FavoriteArticleEntity> findByArticleId(Integer articleId);
	
	Optional<List<FavoriteArticleEntity>> findByArticleTitleLike(String articleTitle);

}
