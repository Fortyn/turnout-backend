package security.service.impl;

import jpa.entity.User;
import jpa.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.model.AuthUser;
import security.service.IUserSecurityService;

import java.util.Set;

/**
 * Implements {@link IUserSecurityService}.
 *
 * @author Sorokin Georgy
 */
@Service
public class UserSecurityServiceImpl implements IUserSecurityService {

  private final UserRepository userRepository;

  public UserSecurityServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public Authentication getAuthentication(String username) {
    return userRepository
               .findByCredentials_Username(username)
               .map(this::mapToAuthUser)
               .map(x -> new UsernamePasswordAuthenticationToken(x, null, Set.of()))
               .orElseThrow(() -> new UsernameNotFoundException("User: [" + username + "] not found in the system"));
  }

  @Override
  public AuthUser findByUsername(String id) {
    return userRepository
            .findByCredentials_Username(id)
               .map(this::mapToAuthUser)
               .orElseThrow();
  }

  private AuthUser mapToAuthUser(User user) {
    final AuthUser authUser = new AuthUser();
    authUser.setFullName(user.getFirstName()+user.getMiddleName()+user.getLastName());
    authUser.setUsername(user.getCredentials().getUsername());
    authUser.setId(user.getId());
    return authUser;
  }

}
