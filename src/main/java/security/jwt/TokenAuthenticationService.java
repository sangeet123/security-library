package security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import security.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by sangeet on 3/6/2017.
 */
public class TokenAuthenticationService {

  private final String TOKEN_PREFIX = "Bearer";
  private final String HEADER = "Authorization";
  private final String USER_ACCOUNT = "userAccount";

  public void addAuthentication(final HttpServletResponse response, final String username,
      Collection<? extends GrantedAuthority> roles) {
    final Account account = SecurityUtils.getAccountFromCredentials(username, roles);
    final String JWT = Jwts.builder().setSubject(username).claim(USER_ACCOUNT, account)
        .setExpiration(new Date(System.currentTimeMillis() + TokenConfig.getExpirationTime()))
        .signWith(SignatureAlgorithm.HS512, TokenConfig.getSecretKey()).compact();
    response.addHeader(HEADER, TOKEN_PREFIX + " " + JWT);
  }

  public Authentication getAuthentication(final HttpServletRequest request) {
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

}
