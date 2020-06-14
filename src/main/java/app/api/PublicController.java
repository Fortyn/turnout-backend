package app.api;

import jpa.entity.User;
import jpa.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Georgy Sorokin
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserRepository userRepository;

    public PublicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public User getUserById(@PathVariable Integer id){
        final var user = userRepository.findById(id).orElseThrow();
        return user;
    }
}
