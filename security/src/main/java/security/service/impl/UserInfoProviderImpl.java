package security.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import security.model.Details;
import security.service.UserInfoProvider;

/**
 * Provider of current system user login.
 */
@Component
public class UserInfoProviderImpl implements UserInfoProvider {
  @Override
  public Details getCurrentUserDetail() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (Details) authentication.getDetails();
  }

}
