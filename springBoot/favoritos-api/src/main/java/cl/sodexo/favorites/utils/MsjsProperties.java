package cl.sodexo.favorites.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@Component
@EnableConfigurationProperties
@PropertySource(value = {"classpath:msjs.properties"})
@ConfigurationProperties(prefix = "msjs")
@Data
@NoArgsConstructor
public class MsjsProperties {

	private String version;
	private String invalidParam;
	private String actionRequest;
	private String requestFoundArticle;
	private String requestNotFoundArticle;
		
}
