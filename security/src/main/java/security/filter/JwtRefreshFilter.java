package security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import security.Url;
import security.service.ITokenService;
import security.service.IUserSecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sends new access-token and refresh-token to the user
 * if his refresh token is correct and valid.
 *
 * @author Sorokin Georgy
 */
public final class JwtRefreshFilter extends AbstractAuthenticationProcessingFilter {

  private final ITokenService tokenService;
  private final IUserSecurityService userService;

  /**
   * Constructor. All parameters are required.
   * Forbids to create session, sets URL, which can be used to login user.
   * @param authManager authenticates user by his login and password.
   * @param successHandler forms and sends access- and refresh-token to user
   *                      if authentication is success.
   * @param tokenService is used to get token from request body
   * @param userService checks login which must be contained in database
   */
  public JwtRefreshFilter(AuthenticationManager authManager,
                          AuthenticationSuccessHandler successHandler,
                          ITokenService tokenService,
                          IUserSecurityService userService) {
    super(Url.REFRESH);
    this.tokenService = tokenService;
    this.userService = userService;
    this.setAllowSessionCreation(false);
    this.setAuthenticationManager(authManager);
    this.setAuthenticationSuccessHandler(successHandler);
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request,
                                              final HttpServletResponse response)
      throws AuthenticationException {
    final var token = tokenService.readToken(request);

    if (token == null || !token.getBody().containsKey("refresh")) {
      response.setStatus(453);
      return null;
    }

    final String subject = token.getBody().getSubject();
    try {
      return userService.getAuthentication(subject);
    } catch (UsernameNotFoundException e) {
      response.setStatus(453);
      return null;
    }
  }

}
