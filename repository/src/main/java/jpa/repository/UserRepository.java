package jpa.repository;

import jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Georgy Sorokin
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByCredentials_Username(String username);
}
