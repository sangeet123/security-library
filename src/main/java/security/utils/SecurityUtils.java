package security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import security.jwt.Account;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by sangeet on 3/9/2017.
 */
public class SecurityUtils {
  private static final ObjectMapper oMapper = new ObjectMapper();

  public static Account getAccountFromCredentials(final String username, final long userId,
      final Collection<? extends GrantedAuthority> authorities) {
    final Account account = new Account();
    return account.setAuthorities(
        authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).setUsername(username).setId(userId);
  }

  public static Account getAccountFromTokenObject(final Object obj) {
    return oMapper.convertValue(obj, Account.class);
  }
}
