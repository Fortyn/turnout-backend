package jpa.repository;

import jpa.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Georgy Sorokin
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
