package security.jwt;

import java.util.List;

/**
 * Created by sangeet on 3/9/2017.
 */
public class Account {
  private String username;
  private List<String> authorities;

  public String getUsername() {
    return username;
  }

  public Account setUsername(final String username) {
    this.username = username;
    return this;
  }

  public List<String> getAuthorities() {
    return authorities;
  }

  public Account setAuthorities(final List<String> authorities) {
    this.authorities = authorities;
    return this;
  }
}
