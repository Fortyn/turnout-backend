package app;

import jpa.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import security.SecurityConfig;

/**
 * @author Georgy Sorokin
 */
@SpringBootApplication
@Import({JpaConfig.class, SecurityConfig.class})
public class Application {
    /**
     * Application entry point.
     */
    public static void main(final String[] args) {
        final var application = new SpringApplication(Application.class);
        application.run(args);
    }
}
