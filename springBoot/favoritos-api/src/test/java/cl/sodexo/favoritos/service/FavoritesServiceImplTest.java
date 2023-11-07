package cl.sodexo.favoritos.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cl.sodexo.favorites.FavoritosApiApplication;
import cl.sodexo.favorites.entity.FavoriteArticleEntity;
import cl.sodexo.favorites.exception.FavoritesException;
import cl.sodexo.favorites.model.ArticleRequest;
import cl.sodexo.favorites.model.ArticleResponse;
import cl.sodexo.favorites.repository.ArticleRepository;
import cl.sodexo.favorites.service.IFavoritesService;
import cl.sodexo.favorites.utils.MsjsProperties;
import cl.sodexo.favoritos.util.CreatedMock;

/**
 * @author NB-AGUTIERREZ
 *
 */
@SpringBootTest(classes = FavoritosApiApplication.class)
class FavoritesServiceImplTest {
	
	private static final int UNA_VEZ = 1;

	@Autowired
	private IFavoritesService favoritesService;
	
	@MockBean
	private ArticleRepository articleRepository;
    
	@Autowired
	private MsjsProperties msjsProperties;

    @DisplayName("Test All Favorites")
	@Test
	void getAllFavoritesTest() {		
    	
    	FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		Mockito.when(this.articleRepository.findAll()).thenReturn(Arrays.asList(favoriteArticle));
		List<ArticleResponse> lst =favoritesService.getFavorites("");		
		verify(this.articleRepository, new Times(UNA_VEZ)).findAll();
		assertNotNull(lst);
		assertFalse(lst.isEmpty());
	}
    
    @DisplayName("Test Favorite By Title")
	@Test
	void getFavoriteTest() {		
    	String title="Title";
    	//String titleLike="%"+title+"%";
		
	 	FavoriteArticleEntity favoriteArticle1 = CreatedMock.createFavoriteArticleEntity(1,12,"Title noticia","{}",LocalDateTime.now());
	 	FavoriteArticleEntity favoriteArticle2 = CreatedMock.createFavoriteArticleEntity(2,123,"Title noticia","{}",LocalDateTime.now());
	 	List<FavoriteArticleEntity> favoriteArticles = new ArrayList<FavoriteArticleEntity> ();
	 	favoriteArticles.add(favoriteArticle1);
	 	favoriteArticles.add(favoriteArticle2);
	 	
		Mockito.when(this.articleRepository.findByArticleTitleLike(anyString())).thenReturn(Optional.of(favoriteArticles));
		List<ArticleResponse> lst = favoritesService.getFavorites(title);		
		verify(this.articleRepository, new Times(UNA_VEZ)).findByArticleTitleLike(anyString());
		assertNotNull(lst);
		assertFalse(lst.isEmpty());
	}
    
    @DisplayName("Test Save Favorites - Ok")
	@Test
	void saveFavoritesOkTest() {		
    	Integer articleId=21373;
		String title="How long will Jeff Bezos continue to subsidize his New Shepard rocket?";
		String url="https://arstechnica.com/space/2023/11/as-virgin-galactic-soars-blue-origins-new-shepard-remains-grounded/";
		String imageUrl="https://cdn.arstechnica.net/wp-content/uploads/2021/10/GettyImages-1329731025-1.jpg";
		String newsSite="Arstechnica";
		String summary="It's definitely a money loser. Always has been.";
		String publishedAt="2023-11-03T16:06:27Z";
		String updatedAt="2023-11-03T16:24:25.809000Z";
		String featured="false";
		List<String> launches = new ArrayList<String>();
		List<String> events = new ArrayList<String>();
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(articleId, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
    	FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,articleId,"Title noticia","{}",LocalDateTime.now());
		Mockito.when(this.articleRepository.findByArticleId(articleId)).thenReturn(Optional.ofNullable(null));
		Mockito.when(this.articleRepository.save(any(FavoriteArticleEntity.class))).thenReturn(favoriteArticle);

	    favoritesService.saveFavorites(articleRequest);

        // Verify that articleRepository.save was called once with the correct argument
        /*verify(articleRepository, new Times(UNA_VEZ)).save(argThat(entity ->
            entity.getArticleId().equals(articleId) &&
            entity.getArticleTitle().equals(articleRequest.getTitle()) &&
            true
        ));*/
	    verify(this.articleRepository, new Times(UNA_VEZ)).findByArticleId(anyInt());
	    verify(articleRepository, new Times(UNA_VEZ)).save(any(FavoriteArticleEntity.class));
        

	}
    
    @DisplayName("Test Save Favorites - Not Found Id")
	@Test
	void saveFavoritesErrorTest() {		
    	Integer articleId=21373;
		String title="How long will Jeff Bezos continue to subsidize his New Shepard rocket?";
		String url="https://arstechnica.com/space/2023/11/as-virgin-galactic-soars-blue-origins-new-shepard-remains-grounded/";
		String imageUrl="https://cdn.arstechnica.net/wp-content/uploads/2021/10/GettyImages-1329731025-1.jpg";
		String newsSite="Arstechnica";
		String summary="It's definitely a money loser. Always has been.";
		String publishedAt="2023-11-03T16:06:27Z";
		String updatedAt="2023-11-03T16:24:25.809000Z";
		String featured="false";
		List<String> launches = new ArrayList<String>();
		List<String> events = new ArrayList<String>();
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(articleId, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
    	FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,articleId,"Title noticia","{}",LocalDateTime.now());
		Mockito.when(this.articleRepository.findByArticleId(articleId)).thenReturn(Optional.ofNullable(favoriteArticle));
		
		FavoritesException favoritesException = Assertions.assertThrows(FavoritesException.class, () -> favoritesService.saveFavorites(articleRequest));

		Assertions.assertEquals(String.format(msjsProperties.getRequestFoundArticle(),articleId),  favoritesException.getMessage());
        verify(articleRepository).findByArticleId(anyInt());
	}
}
