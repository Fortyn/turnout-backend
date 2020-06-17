package jpa.repository;

import jpa.entity.Turnout;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Georgy Sorokin
 */
public interface TurnoutRepository extends JpaRepository<Turnout, Integer> {
}
