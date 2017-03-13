package security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import security.entity.User;
import security.repository.UserRepository;
import security.utils.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by sangeet on 3/6/2017.
 */
@Component() public class TokenAuthenticationService {
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER = "Authorization";
  private static final String USER_ACCOUNT = "userAccount";
  private static UserRepository staticUserRepository;
  @Autowired() private UserRepository userRepository;

  public static void addAuthentication(final HttpServletResponse response, final String username,
      Collection<? extends GrantedAuthority> roles) {
    //We will be collecting all the user info to generate jwt token
    //This is because we do not want to inspect database once authentication is done
    final User user = staticUserRepository.findByusername(username);
    final Account account = SecurityUtils.getAccountFromCredentials(user.getUsername(),user.getId(), roles);
    final String JWT = Jwts.builder().setSubject(username).claim(USER_ACCOUNT, account)
        .setExpiration(new Date(System.currentTimeMillis() + TokenConfig.getExpirationTime()))
        .signWith(SignatureAlgorithm.HS512, TokenConfig.getSecretKey()).compact();
    response.addHeader(HEADER, TOKEN_PREFIX + " " + JWT);
  }

  public static Authentication getAuthentication(final HttpServletRequest request) {
    final String token = request.getHeader(HEADER);
    if (token == null) {
      return null;
    }
    try {
      final Object accountObj = Jwts.parser().setSigningKey(TokenConfig.getSecretKey())
          .parseClaimsJws(token).getBody().get(USER_ACCOUNT);
      final Account account = SecurityUtils.getAccountFromTokenObject(accountObj);
      return new AuthenticatedUser(account.getUsername(),
          account.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role))
              .collect(Collectors.toList()));
    } catch (final Exception ex) {
      //log the exception
      return null;
    }
  }

  @PostConstruct() public void TokenAuthenticationService() {
    staticUserRepository = this.userRepository;
  }

}
