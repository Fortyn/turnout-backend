package security.model;

import jpa.entity.Credentials;
import jpa.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Implements {@link UserDetails}.
 * Contains user's data: username(login), password and authorities.
 */
public class Details implements UserDetails {
  private static final long serialVersionUID = -8434831582045464489L;
  private final String username;
  private final String password;
  private final Integer userId;
  private final Collection<SimpleGrantedAuthority> authorities;

  /**
   * Constructor.
   * All parameters are required.
   */
  public Details(Credentials master) {
    username = master.getUsername();
    password = new String(master.getHash());
    userId = master.getId();
    authorities = Set.of();
  }

  public Details(User user) {
    username = user.getFirstName();
    password = "";
    userId = user.getId();
    authorities = Set.of();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Integer getUserId() {
    return userId;
  }
}
