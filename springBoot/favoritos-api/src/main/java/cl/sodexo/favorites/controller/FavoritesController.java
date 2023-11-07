package cl.sodexo.favorites.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.sodexo.favorites.controller.error.ApiError;
import cl.sodexo.favorites.model.ArticleRequest;
import cl.sodexo.favorites.model.ArticleResponse;
import cl.sodexo.favorites.service.IFavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@RestController
@RequestMapping(path = "${servicio.path}")
@CrossOrigin(origins = "*")
@Tag(name = "${servicio.name}", description = "${servicio.description}")
public class FavoritesController {

	@Autowired
	private IFavoritesService favoritesService;
	
	/*
	 * 
	 * */
	@GetMapping(value="/{partialArticleTitle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "${servicio.app-favorites-get.name}", description = "${servicio.app-favorites-get.description}")
    @ApiResponses(
    		value = { 
    				@ApiResponse(responseCode = "200", description = "${servicio.status-200-description}"),    				
    				@ApiResponse(responseCode = "422", description = "${servicio.status-422-description}", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))) 
    		}
    )	
	public ResponseEntity<List<ArticleResponse>> getFavorites(
			//@Parameter(description = "Partial Article Title", name = "partialArticleTitle", schema = @Schema(type = "string", required = true, defaultValue = ""), in = ParameterIn.PATH) 
			@Valid @PathVariable("partialArticleTitle") @NotBlank String partialArticleTitle) {
		    
		return new ResponseEntity<List<ArticleResponse>>(favoritesService.getFavorites(partialArticleTitle),HttpStatus.OK);
	}
	
	/*
	 * 
	 * */
	@GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "${servicio.app-favorites-get.name}", description = "${servicio.app-favorites-get.description}")
    @ApiResponses(
    		value = { 
    				@ApiResponse(responseCode = "200", description = "${servicio.status-200-description}"),    				
    				@ApiResponse(responseCode = "422", description = "${servicio.status-422-description}", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))) 
    		}
    )
	public ResponseEntity<List<ArticleResponse>> getAllFavorites() {		  
		
		return new ResponseEntity<List<ArticleResponse>>(favoritesService.getFavorites(""),HttpStatus.OK);
	}
	
 	
	/*
	 * 
	 * */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "${servicio.app-favorites-post.name}", description = "${servicio.app-favorites-post.description}")
		@ApiResponses(
			value = { 
					@ApiResponse(responseCode = "200", description = "${servicio.status-200-description}"),				
					@ApiResponse(responseCode = "422", description = "${servicio.status-422-description}", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
			}
	)
	public ResponseEntity<Void> saveFavorite(
			@Parameter(in = ParameterIn.DEFAULT, description = "${servicio.app-favorites-post.params-description}", required=true, schema=@Schema(implementation = ArticleRequest.class))
			@Valid @RequestBody ArticleRequest body) {
	
		favoritesService.saveFavorites(body);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/*
	 * 
	 * */
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "${servicio.app-favorites-put.name}", description = "${servicio.app-favorites-put.description}", tags = { "" })
    @ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "${servicio.status-200-description}"),				
			@ApiResponse(responseCode = "422", description = "${servicio.status-422-description}", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class)))
            }
    )
    public ResponseEntity<Void> updateFavorite(
 			@Parameter(in = ParameterIn.DEFAULT, description = "${servicio.app-favorites-put.params-description}", required=true, schema=@Schema(implementation = ArticleRequest.class))
 			@Valid @RequestBody ArticleRequest body) {
		
		favoritesService.updateFavorites(body);
        return new ResponseEntity<Void>(HttpStatus.OK);     
    }	
	
	
	/*
	 * 
	 * */	
	@DeleteMapping(value="/{articleId}")
	@Operation(summary = "${servicio.app-favorites-delete.name}", description = "${servicio.app-favorites-delete.description}", tags = { "" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "${servicio.status-200-description}", content = @Content(schema = @Schema(implementation = Void.class))),    	            
            @ApiResponse(responseCode = "422", description = "${servicio.status-422-description}", content = @Content(schema = @Schema(implementation = ApiError.class)))           
    })
	public ResponseEntity<Void> deleteFavorite(
			@Parameter( name = "articleId", description = "Article Id", schema = @Schema(type = "integer", required = true, defaultValue = "0"), in = ParameterIn.PATH) 
			@PathVariable @Positive Integer articleId) {
		
		favoritesService.deleteFavorites(articleId);
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
 
}
