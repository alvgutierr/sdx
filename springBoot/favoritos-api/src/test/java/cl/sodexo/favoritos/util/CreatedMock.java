package cl.sodexo.favoritos.util;

import java.time.LocalDateTime;
import java.util.List;

import cl.sodexo.favorites.entity.FavoriteArticleEntity;
import cl.sodexo.favorites.model.ArticleRequest;


public class CreatedMock {

	public static FavoriteArticleEntity createFavoriteArticleEntity(Integer id, Integer articleId, String articleTitle, String dataArticle, LocalDateTime favoriteCreateDatetime){
		return FavoriteArticleEntity.builder().id(id).articleId(articleId).articleTitle(articleTitle).dataArticle(dataArticle).favoriteCreateDatetime(favoriteCreateDatetime).build();
	}

	public static ArticleRequest createArticleRequest(Integer id,	String title, String url, String imageUrl,
			String newsSite,String summary, String publishedAt, String updatedAt, String featured, List<String> launches, List<String> events) {
		
		return ArticleRequest.builder()
				.id(id)
				.title(title)
				.url(imageUrl)
				.imageUrl(imageUrl)
				.newsSite(newsSite)
				.summary(summary)
				.publishedAt(publishedAt)
				.updatedAt(updatedAt)
				.featured(featured)
				.launches(launches)
				.events(events).build();
		
	}

}
