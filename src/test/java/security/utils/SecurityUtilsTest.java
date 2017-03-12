package security.utils;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import security.jwt.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by sangeet on 3/11/2017.
 */
public class SecurityUtilsTest {

  @Test() public void TestGetAccountFromCredentials() {
    final String username = "fakeuser";
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    final SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_TEST_USER");
    final SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_QA");
    authorities.add(authority1);
    authorities.add(authority2);
    final Account account = SecurityUtils.getAccountFromCredentials(username, authorities);
    assertEquals(username, account.getUsername());
    assertEquals(Arrays.asList("ROLE_TEST_USER", "ROLE_QA"), account.getAuthorities());
  }

  @Test() public void TestGetAccountFromTokenObject() {
    final String username = "fakeuser";
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    final SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_TEST_USER");
    final SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_QA");
    authorities.add(authority1);
    authorities.add(authority2);
    final Account account = SecurityUtils.getAccountFromCredentials(username, authorities);

    final Account accountReceived = SecurityUtils.getAccountFromTokenObject(account);
    assertEquals("fakeuser", accountReceived.getUsername());
    assertEquals(account.getAuthorities(), accountReceived.getAuthorities());
  }
}
