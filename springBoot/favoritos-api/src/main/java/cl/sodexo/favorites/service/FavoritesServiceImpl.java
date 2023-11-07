package cl.sodexo.favorites.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sodexo.favorites.entity.FavoriteArticleEntity;
import cl.sodexo.favorites.exception.FavoritesException;
import cl.sodexo.favorites.model.ArticleRequest;
import cl.sodexo.favorites.model.ArticleResponse;
import cl.sodexo.favorites.repository.ArticleRepository;
import cl.sodexo.favorites.utils.FnUtil;
import cl.sodexo.favorites.utils.MsjsProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@Service
@Slf4j
public class FavoritesServiceImpl implements IFavoritesService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private MsjsProperties msjsProperties;
	
	/**
	 * Obtiene una lista de noticias favoritos que coinciden con una parte del título.
	 *
	 * @param favoritesPartialTitle La parte del título de las noticias favoritas a buscar.
	 * @return Una lista de respuestas de noticias favoritas.
	 */
	@Override
	public List<ArticleResponse> getFavorites(String favoritesPartialTitle) {
	    List<FavoriteArticleEntity> favoriteArticles = new ArrayList<>();

	    if (!FnUtil.isNullOrEmpty(favoritesPartialTitle)) {
	        // Buscar artículos favoritos por una parte del título
	        Optional<List<FavoriteArticleEntity>> dataArticleDb = articleRepository.findByArticleTitleLike("%" + favoritesPartialTitle.toLowerCase() + "%");

	        if (dataArticleDb.isPresent()) {
	            favoriteArticles = dataArticleDb.get();
	        }
	    } else {
	        // Si no se proporciona una parte del título, obtener todos los artículos favoritos
	        favoriteArticles = articleRepository.findAll();
	    }

	    log.info("Lista de artículos favoritos: " + favoriteArticles);

	    // Mapear y convertir los artículos favoritos en respuestas de artículos
	    return favoriteArticles.stream()
	            .map(FnUtil.ArticleToResponse::apply)
	            .collect(Collectors.toList());
	}
	
	/**
	 * Guarda una noticia favorita en la base de datos.
	 *
	 * @param favoritesRq La solicitud que contiene los datos de la noticia favorita a guardar.
	 */
	@Override
	public void saveFavorites(ArticleRequest favoritesRq) {
	    // Validar si ya se ha guardado un artículo con el mismo ID
	    validatedPreviusSaveArticle(favoritesRq.getId());

	    // Crear una instancia de FavoriteArticleEntity con los datos de la solicitud
	    FavoriteArticleEntity oFavoriteArticle = FavoriteArticleEntity.builder()
	            .articleId(favoritesRq.getId())
	            .articleTitle(favoritesRq.getTitle().toLowerCase())
	            .dataArticle(FnUtil.createObjectToJsonString(favoritesRq))
	            .favoriteCreateDatetime(LocalDateTime.now())
	            .build();

	    log.info("Artículo favorito a guardar: " + oFavoriteArticle);

	    // Guardar el artículo favorito en la base de datos
	    articleRepository.save(oFavoriteArticle);
	}

	/**
	 * Elimina una noticia favorita de la base de datos.
	 *
	 * @param articleId El identificador de la noticia favorita a eliminar.
	 */
	@Override
	public void deleteFavorites(Integer articleId) {
	    // Validar si el artículo favorito existe antes de eliminarlo
	    FavoriteArticleEntity article = validateExistingArticle(articleId);
	    log.info("Artículo favorito a eliminar: " + article);
	    // Eliminar el artículo favorito de la base de datos
	    articleRepository.delete(article);
	}
	
	/**
	 * Actualiza una noticia favorita en la base de datos con nuevos datos.
	 *
	 * @param favoritesRq La solicitud que contiene los nuevos datos de la noticia favorita.
	 */
	@Override
	public void updateFavorites(ArticleRequest favoritesRq) {
	    // Validar si el artículo favorito existe antes de actualizarlo
	    FavoriteArticleEntity article = validateExistingArticle(favoritesRq.getId());

	    log.info("Artículo favorito a actualizar: " + article);

	    // Actualizar los datos del artículo favorito con la información de la solicitud
	    article.setArticleTitle(favoritesRq.getTitle());
	    article.setDataArticle(FnUtil.createObjectToJsonString(favoritesRq));
	    article.setFavoriteCreateDatetime(LocalDateTime.now());

	    log.info("Artículo favorito actualizado: " + article);

	    // Guardar el artículo favorito actualizado en la base de datos
	    articleRepository.save(article);
	}
	
	/**
	 * Valida si ya existe una noticia con el mismo ID antes de guardarla.
	 *
	 * @param articleId El ID de la noticia a validar.
	 * @throws FavoritesException Si se encuentra una noticia con el mismo ID.
	 */
	private void validatedPreviusSaveArticle(Integer articleId) {
	    Optional<FavoriteArticleEntity> dataArticleDb = articleRepository.findByArticleId(articleId);

	    if (dataArticleDb.isPresent()) {
	        throw new FavoritesException(msjsProperties.getActionRequest(),
	                String.format(msjsProperties.getRequestFoundArticle(), articleId));
	    }
	}

	/**
	 * Valida si una noticia favorita con el ID especificado existe en la base de datos.
	 *
	 * @param articleId El ID de la noticia a validar.
	 * @return La entidad de la noticia favorita si se encuentra.
	 * @throws FavoritesException Si no se encuentra una noticia con el ID especificado.
	 */
	private FavoriteArticleEntity validateExistingArticle(Integer articleId) {
	    return articleRepository.findByArticleId(articleId)
	            .orElseThrow(() -> new FavoritesException(msjsProperties.getActionRequest(),
	                    String.format(msjsProperties.getRequestNotFoundArticle(), articleId)));
	}
}
