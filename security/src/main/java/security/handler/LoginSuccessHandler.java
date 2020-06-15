package security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import security.model.AuthResponse;
import security.model.AuthResponseWithUser;
import security.model.AuthUser;
import security.service.ITokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**

 * @author Sorokin Georgy
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final ITokenService tokenService;
  private final ObjectMapper mapper;

  /**
   * Constructor.
   * Parameter is required.
   *
   * @param tokenService generates authentication response which contains access- and refresh-token.
   */
  public LoginSuccessHandler(final ITokenService tokenService, ObjectMapper mapper) {
    this.tokenService = tokenService;
    this.mapper = mapper;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) throws IOException {
    response.setCharacterEncoding(request.getCharacterEncoding());
    final var currentUser = (AuthUser) authentication.getPrincipal();
    final String id = currentUser.getUsername();
    final AuthResponse authResponse = tokenService.generateToken(id);
    response.setStatus(HttpServletResponse.SC_OK);

    try (var writer = response.getWriter()) {
      mapper.writeValue(writer, new AuthResponseWithUser(authResponse, currentUser));
    }
  }
}
