package security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sangeet on 3/6/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  public JWTLoginFilter(final String url, final AuthenticationManager authenticationManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authenticationManager);
  }

  @Override() public Authentication attemptAuthentication(
      final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse)
      throws AuthenticationException, IOException, ServletException {
    final AccountCredentials credentials = new ObjectMapper()
        .readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
    final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        credentials.getUsername(), credentials.getPassword());
    return getAuthenticationManager().authenticate(token);
  }

  @Override() protected void successfulAuthentication(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain,
      final Authentication authentication) throws IOException, ServletException {
    TokenAuthenticationService
        .addAuthentication(response, authentication.getName(), authentication.getAuthorities());
  }
}
