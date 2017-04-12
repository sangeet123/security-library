package security.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sangeet on 4/11/2017.
 */
@Entity() @Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "username",
    "role" }) }, name = "user_roles", schema = "userdb") public class UserRole
    implements Serializable {
  @Id() @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") public Long id;

  @Column(name = "role") public String role;

  @ManyToOne(optional = false) @JoinColumn(name = "username", referencedColumnName = "username") private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public User getUser() {
    return user;
  }

  public void setUser(security.entity.User user) {
    this.user = user;
  }
}
