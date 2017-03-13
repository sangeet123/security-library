package security.entity;

import javax.persistence.*;

/**
 * Created by sangeet on 3/12/2017.
 */
@Entity()
@Table(name="users")
public class User {
  @Id()
  @GeneratedValue()
  @Column(name = "id")
  public long id;

  @Column(name = "username", unique = true)
  public String username;

  @Column(name = "password")
  public String password;

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  @Override() public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    User user = (User) o;

    return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, user.id)
        .append(username, user.username).append(password, user.password).isEquals();
  }

  @Override public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(id).append(username)
        .append(password).toHashCode();
  }
}
