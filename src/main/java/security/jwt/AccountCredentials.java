package security.jwt;

/**
 * Created by sangeet on 3/6/2017.
 */
public class AccountCredentials {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public AccountCredentials setUsername(final String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public AccountCredentials setPassword(final String password) {
    this.password = password;
    return this;
  }
}
