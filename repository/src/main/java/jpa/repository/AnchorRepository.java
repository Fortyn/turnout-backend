package jpa.repository;

import jpa.entity.Anchor;
import jpa.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Georgy Sorokin
 */
public interface AnchorRepository extends JpaRepository<Anchor, Integer> {
    List<Anchor> findAllByRoomsContains(Room room);
}
