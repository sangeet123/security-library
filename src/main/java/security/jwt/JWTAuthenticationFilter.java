package security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by sangeet on 3/6/2017.
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

  @Override() public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain filterChain) throws IOException, ServletException {
    final Authentication authentication = new TokenAuthenticationService()
        .getAuthentication((HttpServletRequest) request);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
