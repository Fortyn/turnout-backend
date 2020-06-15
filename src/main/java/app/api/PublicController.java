package app.api;

import jpa.entity.Room;
import jpa.entity.User;
import jpa.repository.RoomRepository;
import jpa.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Georgy Sorokin
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    private final RoomRepository roomRepository;

    public PublicController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }
}
