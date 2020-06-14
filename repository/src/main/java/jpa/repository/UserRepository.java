package jpa.repository;

import jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Georgy Sorokin
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
