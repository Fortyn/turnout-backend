package jpa.repository;

import jpa.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Georgy Sorokin
 */
public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
    Optional<Credentials> findByUsername(String username);
}
