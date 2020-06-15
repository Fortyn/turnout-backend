package security.model;


/**
 * Keeps user fields needed to create Authentication object.
 *
 * @author Sorokin Georgy
 */
public class AuthUser {
  private Integer id;
  private String username;
  private String fullName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
