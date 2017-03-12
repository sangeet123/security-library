package security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by sangeet on 3/6/2017.
 */
public class AuthenticatedUser implements Authentication {

  private String name;
  private Collection<? extends GrantedAuthority> roles;
  private boolean authenticated = true;

  AuthenticatedUser(final String name, Collection<? extends GrantedAuthority> roles) {
    this.name = name;
    this.roles = roles;
  }

  @Override() public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override() public Object getCredentials() {
    return null;
  }

  @Override() public Object getDetails() {
    return null;
  }

  @Override() public Object getPrincipal() {
    return null;
  }

  @Override() public boolean isAuthenticated() {
    return this.authenticated;
  }

  @Override() public void setAuthenticated(final boolean b) throws IllegalArgumentException {
    this.authenticated = b;
  }

  @Override() public String getName() {
    return this.name;
  }
}
