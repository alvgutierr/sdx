package cl.sodexo.favorites.service;

import java.util.List;

import cl.sodexo.favorites.model.ArticleRequest;
import cl.sodexo.favorites.model.ArticleResponse;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
public interface IFavoritesService {
	
	void saveFavorites(ArticleRequest favRq);
	
	List<ArticleResponse> getFavorites(String favoritesPartialTitle);
	
	void updateFavorites(ArticleRequest favRq);
	
	void deleteFavorites(Integer favoritesId);

}
