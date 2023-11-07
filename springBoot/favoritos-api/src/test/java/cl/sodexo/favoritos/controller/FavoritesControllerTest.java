/**
 * 
 */
package cl.sodexo.favoritos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.sodexo.favorites.FavoritosApiApplication;
import cl.sodexo.favorites.entity.FavoriteArticleEntity;
import cl.sodexo.favorites.model.ArticleRequest;
import cl.sodexo.favorites.repository.ArticleRepository;
import cl.sodexo.favorites.utils.MsjsProperties;
import cl.sodexo.favoritos.util.CreatedMock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NB-AGUTIERREZ
 *
 */
@ContextConfiguration(classes = {MsjsProperties.class})
@SpringBootTest(classes = FavoritosApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@Slf4j
class FavoritesControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArticleRepository articleRepository;

	@DisplayName("build Test HTTP 200 All Favorites")
	@Test
	void allFavoritesHttp200Test()  throws Exception{
	 	FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		Mockito.when(this.articleRepository.findAll()).thenReturn(Arrays.asList(favoriteArticle));

        String r = "/api/v1/favorites/";
		String response = mvc
				.perform(MockMvcRequestBuilders.get(r).contentType(MediaType.APPLICATION_JSON)
						.content(""))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        log.info(response);
		assertThat(response).contains("articleId");		
	}
	
	@DisplayName("build Test HTTP 200 Favorite By Title")
	@Test
	void favoriteByTitleHttp200Test()  throws Exception{
		String title="title";
	 	FavoriteArticleEntity favoriteArticle1 = CreatedMock.createFavoriteArticleEntity(1,12,"Title noticia","{}",LocalDateTime.now());
	 	FavoriteArticleEntity favoriteArticle2 = CreatedMock.createFavoriteArticleEntity(2,123,"Title noticia","{}",LocalDateTime.now());
	 	FavoriteArticleEntity favoriteArticle3 = CreatedMock.createFavoriteArticleEntity(3,1234,"Title noticia","{}",LocalDateTime.now());
	 	FavoriteArticleEntity favoriteArticle4 = CreatedMock.createFavoriteArticleEntity(4,12345,"Title noticia","{}",LocalDateTime.now());
	 	List<FavoriteArticleEntity> favoriteArticles = new ArrayList<FavoriteArticleEntity> ();
	 	favoriteArticles.add(favoriteArticle1);
	 	favoriteArticles.add(favoriteArticle2);
	 	favoriteArticles.add(favoriteArticle3);
	 	favoriteArticles.add(favoriteArticle4);
	 	
		Mockito.when(this.articleRepository.findByArticleTitleLike("%"+title+"%")).thenReturn(Optional.of(favoriteArticles));

        String url = "/api/v1/favorites/"+title;
		String response = mvc
				.perform(MockMvcRequestBuilders.get(url)
						.param("partialArticleTitle", title)
						.contentType(MediaType.APPLICATION_JSON)
						.content(""))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		
        log.info(response);
		assertThat(response).contains("articleId");
        //assertThat(response).contains("[]");        
	}
	
	/* Lo elimino pq si no encuantra nada retorna vacio
	@DisplayName("build Test HTTP 422 Favorite By Title")
	@Test
	void favoriteByTitleHttp422Test()  throws Exception{
	 	
		//Mockito.when(this.articleRepository.findByArticleTitleLike("%title%")).thenReturn(Optional.ofNullable(null));
		//Mockito.when(this.articleRepository.findByArticleTitleLike("%title%")).thenThrow(Optional.of(null));
        String url = "/api/v1/favorites/title";
		String response = mvc
				.perform(MockMvcRequestBuilders.get(url)
						.param("partialArticleTitle", "title/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(""))
				.andExpect(MockMvcResultMatchers.status().is(422)).andReturn().getResponse().getContentAsString();
		
        log.info(response);
		assertThat(response).contains("No value present");		
	}
	*/
	
	
	@DisplayName("build Test HTTP 200 Create Favorite")
	@Test
	void createFavoriteHttp200Test() throws Exception {
		Integer id=21373;
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
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
		FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		
		//Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(favoriteArticle));
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(null));
		Mockito.when(this.articleRepository.save(any(FavoriteArticleEntity.class))).thenReturn(favoriteArticle);

		String response = mvc
				.perform(MockMvcRequestBuilders.post("/api/v1/favorites/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(articleRequest)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("");
	}
	
	@DisplayName("build Test HTTP 422 Create Favorite")
	@Test
	void createFavoriteHttp422Test() throws Exception {
		Integer id=21373;
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
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
		FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(favoriteArticle));		
		Mockito.when(this.articleRepository.save(any(FavoriteArticleEntity.class))).thenReturn(favoriteArticle);

		String response = mvc
				.perform(MockMvcRequestBuilders.post("/api/v1/favorites/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(articleRequest)))
				.andExpect(MockMvcResultMatchers.status().is(422)).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("Request found for articleId: "+id);
	}
	
	@DisplayName("build Test HTTP 200 Update Favorite")
	@Test
	void updateFavoriteHttp200Test() throws Exception {
		Integer id=21373;
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
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
		FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(favoriteArticle));
		Mockito.when(this.articleRepository.save(any(FavoriteArticleEntity.class))).thenReturn(favoriteArticle);

		String response = mvc
				.perform(MockMvcRequestBuilders.put("/api/v1/favorites/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(articleRequest)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("");
	}
	
	
	@DisplayName("build Test HTTP 422 Update Favorite")
	@Test
	void updateFavoriteHttp422Test() throws Exception {
		Integer id=21373;
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
		ArticleRequest articleRequest = CreatedMock.createArticleRequest(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, featured, launches, events);
		
		FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(null));		
		Mockito.when(this.articleRepository.save(any(FavoriteArticleEntity.class))).thenReturn(favoriteArticle);

		String response = mvc
				.perform(MockMvcRequestBuilders.put("/api/v1/favorites/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(articleRequest)))
				.andExpect(MockMvcResultMatchers.status().is(422)).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("Request not found articleId: "+id);
	}
	
	@DisplayName("build Test HTTP 200 Delete Favorite")
	@Test
	void deleteFavoriteHttp200Test() throws Exception {
		Integer id=21373;
		FavoriteArticleEntity favoriteArticle = CreatedMock.createFavoriteArticleEntity(1,1234,"Title noticia","{}",LocalDateTime.now());
		
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(favoriteArticle));
		doNothing().when(articleRepository).delete(any(FavoriteArticleEntity.class));

		String response = mvc
				.perform(MockMvcRequestBuilders.delete("/api/v1/favorites/"+id).content(""))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("");
	}
	
	@DisplayName("build Test HTTP 422 Delete Favorite")
	@Test
	void deleteFavoriteHttp422Test() throws Exception {
		Integer id=21373;
		Mockito.when(this.articleRepository.findByArticleId(id)).thenReturn(Optional.ofNullable(null));

		String response = mvc
				.perform(MockMvcRequestBuilders.delete("/api/v1/favorites/"+id).content(""))
				.andExpect(MockMvcResultMatchers.status().is(422)).andReturn().getResponse().getContentAsString();
		log.info(response);
		assertThat(response).contains("Request not found articleId: "+id);
	}
}
