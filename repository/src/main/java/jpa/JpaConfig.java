package jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Georgy Sorokin
 */
@Configuration
@EntityScan({"jpa.entity", "jpa.converter"})
@EnableJpaRepositories(basePackages = {"jpa.repository"})
@EnableTransactionManagement
public class JpaConfig {
}
