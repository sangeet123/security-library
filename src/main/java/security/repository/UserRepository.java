package security.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;
import security.entity.User;

/**
 * Created by sangeet on 3/12/2017.
 */
@org.springframework.stereotype.Repository()
public interface UserRepository extends Repository<User, Long> {
  User findByusername(@Param("username")final String username);
}
