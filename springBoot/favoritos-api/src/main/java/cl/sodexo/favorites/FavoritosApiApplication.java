package cl.sodexo.favorites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Proyecto Test Sodexo.
 * <p>Api para atender solitudes desde la web {@link #series()}.
 * @author Alvaro Gutierrez
 * @since 3.0
 * @see </a>
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan({"cl.sodexo.favorites.entity"})
public class FavoritosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoritosApiApplication.class, args);
	}

}
