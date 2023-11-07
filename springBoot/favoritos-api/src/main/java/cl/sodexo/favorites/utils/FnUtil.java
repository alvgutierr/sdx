package cl.sodexo.favorites.utils;

import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.sodexo.favorites.entity.FavoriteArticleEntity;
import cl.sodexo.favorites.model.ArticleResponse;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
public class FnUtil {

	public static final Function<FavoriteArticleEntity, ArticleResponse> ArticleToResponse = fn -> ArticleResponse.builder()
			.id(fn.getId())
			.articleId(fn.getArticleId())
			.articleTitle(fn.getArticleTitle())
			.dataArticle(FnUtil.createJsonStringToObject(fn.getDataArticle()).toString())
			.favoriteCreateDatetime(fn.getFavoriteCreateDatetime())
			.build();

	public static Integer toNumber(String numberStr) {
	    try{
	        return Integer.parseInt(numberStr);
	    }
	    catch (NumberFormatException ex){ 
	    	return 0;
	    }		
	}
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	public static String createObjectToJsonString(Object data) {

		try {
			return new ObjectMapper().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}
	
	public static Object createJsonStringToObject(String data) {

		try {
			return new ObjectMapper().readValue(data, Object.class);
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}

}
