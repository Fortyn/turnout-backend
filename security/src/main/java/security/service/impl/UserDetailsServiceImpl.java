package security.service.impl;

import jpa.repository.CredentialsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.model.Details;

/**
 * Implements {@link UserDetailsService}.
 *
 * @author Sorokin Georgy
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final CredentialsRepository repository;

  /**
   * Constructor.
   * Parameter is required.
   * @param repository is used to find user's data by login
   */
  public UserDetailsServiceImpl(final CredentialsRepository repository) {
    this.repository = repository;
  }

  /**
   * Used in dao service to check user.
   *
   * @param username is user's login
   * @return {@link UserDetails} object with user's login, password and authorities
   * @throws UsernameNotFoundException if user has not been found in repository
   */
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return repository
        .findByUsername(username)
        .map(Details::new)
        .orElseThrow(
            () -> new UsernameNotFoundException("User " + username + "not found"));
  }

}
