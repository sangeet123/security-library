package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import security.entity.UserRole;

/**
 * Created by sangeet on 4/12/2017.
 */
@org.springframework.stereotype.Repository("userRoleRepository") @Transactional("userTransactionManager") public interface UserRoleRepository
    extends JpaRepository<UserRole, Long> {
}
