package security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by sangeet on 3/12/2017.
 */
@Entity() @Table(name = "users", schema = "userdb") public class User implements Serializable {
  @Id() @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") public Long id;

  @Column(name = "username", unique = true) public String username;

  @Column(name = "password") public String password;

  @Column(name = "firstName") public String firstName;

  @Column(name = "lastName") public String lastName;

  @Column(name = "enabled") public Boolean enabled;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = UserRole.class,
      fetch = FetchType.LAZY) private Collection userRoles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(final Boolean enabled) {
    this.enabled = enabled;
  }

  public Collection getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Collection userRoles) {
    this.userRoles = userRoles;
  }

  @Override public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", enabled=" + enabled +
        ", userRoles=" + userRoles +
        '}';
  }
}
