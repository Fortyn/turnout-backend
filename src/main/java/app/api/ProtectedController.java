package app.api;

import app.model.TurnoutRegistrationStatus;
import app.model.TurnoutRequest;
import app.model.TurnoutResponse;
import jpa.entity.Anchor;
import jpa.entity.Room;
import jpa.entity.Turnout;
import jpa.entity.User;
import jpa.repository.AnchorRepository;
import jpa.repository.RoomRepository;
import jpa.repository.TurnoutRepository;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import security.model.AuthUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Georgy Sorokin
 */
@RestController
@RequestMapping("/protected")
public class ProtectedController {

    private final RoomRepository roomRepository;
    private final AnchorRepository anchorRepository;
    private final TurnoutRepository turnoutRepository;
    private final AuthUser authUser;

    public ProtectedController(RoomRepository roomRepository,
                               AnchorRepository anchorRepository,
                               TurnoutRepository turnoutRepository,
                               AuthUser authUser) {
        this.roomRepository = roomRepository;
        this.anchorRepository = anchorRepository;
        this.turnoutRepository = turnoutRepository;
        this.authUser = authUser;
    }


    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    @GetMapping(path = "/rooms/{id}/anchors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Anchor> getAnchor(@PathVariable Integer id){
        final var room = roomRepository.getOne(id);
        return anchorRepository.findAllByRoomsContains(room);
    }

    @PostMapping(path = "/turnout",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public TurnoutResponse registerTurnout(@RequestBody TurnoutRequest model){
        final var optional = anchorRepository.findById(model.getAnchorId());
        if(optional.isEmpty()) {
            return new TurnoutResponse(TurnoutRegistrationStatus.REJECTED);
        }
        final var anchor = optional.get();
        final var value = anchor.getPropertyValue();
        if(value.equals(model.getCharacteristicValue())){
            turnoutRepository.save(createTurnout(model));
            return new TurnoutResponse(TurnoutRegistrationStatus.ACCEPTED);
        }
        return new TurnoutResponse(TurnoutRegistrationStatus.REJECTED);
    }

    private Turnout createTurnout(TurnoutRequest turnoutRequest){
        final var turnout = new Turnout();
        final var user = new User();
        user.setId(authUser.getId());
        turnout.setUser(user);
        final var room = new Room();
        room.setId(turnoutRequest.getRoomId());
        turnout.setRoom(room);
        turnout.setDatetime(LocalDateTime.now());
        return turnout;
    }
}
