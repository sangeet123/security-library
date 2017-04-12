package security.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import security.entity.User;

/**
 * Created by sangeet on 3/12/2017.
 */
@org.springframework.stereotype.Repository() @Transactional("userTransactionManager") public interface UserRepository
    extends Repository<User, Long> {
  User findByusername(@Param("username") final String username);
}
