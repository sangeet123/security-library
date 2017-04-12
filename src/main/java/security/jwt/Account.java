package security.jwt;

import java.util.List;

/**
 * Created by sangeet on 3/9/2017.
 */
public class Account {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private List<String> authorities;

  public String getUsername() {
    return username;
  }

  public Account setUsername(final String username) {
    this.username = username;
    return this;
  }

  public Long getId() {
    return id;
  }

  public Account setId(Long id) {
    this.id = id;
    return this;
  }

  public List<String> getAuthorities() {
    return authorities;
  }

  public Account setAuthorities(final List<String> authorities) {
    this.authorities = authorities;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Account setFirstName(final String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Account setLastName(final String lastName) {
    this.lastName = lastName;
    return this;
  }
}
