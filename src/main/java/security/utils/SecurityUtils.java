package security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import security.entity.User;
import security.jwt.Account;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by sangeet on 3/9/2017.
 */
public class SecurityUtils {
  private static final ObjectMapper oMapper = new ObjectMapper();

  public static Account getAccountFromCredentials(final User user,
      final Collection<? extends GrantedAuthority> authorities) {
    final Account account = new Account();
    return account.setAuthorities(
        authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .setUsername(user.getUsername()).setId(user.getId()).setFirstName(user.getFirstName())
        .setLastName(user.getLastName());
  }

  public static Account getAccountFromTokenObject(final Object obj) {
    return oMapper.convertValue(obj, Account.class);
  }
}
