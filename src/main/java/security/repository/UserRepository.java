package security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import security.entity.User;

/**
 * Created by sangeet on 3/12/2017.
 */
@org.springframework.stereotype.Repository("userRepository") @Transactional("userTransactionManager") public interface UserRepository
    extends PagingAndSortingRepository<User, Long> {
  User findByusername(@Param("username") final String username);
}
