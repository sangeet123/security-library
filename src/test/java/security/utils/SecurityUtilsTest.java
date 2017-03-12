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
    Collection<GrantedAuthority> roles = new ArrayList<>();
    final SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_TEST_USER");
    final SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("ROLE_QA");
    roles.add(role1);
    roles.add(role2);
    final Account account = SecurityUtils.getAccountFromCredentials(username, roles);
    assertEquals(username, account.getUsername());
    assertEquals(Arrays.asList("ROLE_TEST_USER", "ROLE_QA"), account.getAuthorities());
  }

  @Test() public void TestGetAccountFromTokenObject() {
    final Object tokenObj = "{\"username\":\"fakeuser\",\"authorities\":[\"ROLE_TEST_USER\",\"ROLE_QA\"]}";
    final Account account = SecurityUtils.getAccountFromTokenObject(tokenObj);
    assertEquals("fakeuser", account.getUsername());
    assertEquals(Arrays.asList("ROLE_TEST_USER", "ROLE_QA"), account.getAuthorities());
  }
}
