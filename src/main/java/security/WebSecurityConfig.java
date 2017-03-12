package security;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.jwt.JWTAuthenticationFilter;
import security.jwt.JWTLoginFilter;

/**
 * Created by sangeet on 3/6/2017.
 */
@ConditionalOnExpression("${security.enabled:false}") @Configuration() @EnableWebSecurity() @EnableGlobalMethodSecurity(securedEnabled = true) public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter {

  private final String USER_NAME_BY_USER_NAME_QUERY = "select username,password, enabled from users where username=?";
  private final String AUTHORITIES_BY_USER_NAME_QUERY = "select username, role from user_roles where username=?";

  @Autowired() private DataSource dataSource;

  protected void configure(final HttpSecurity http) throws Exception {
    // disable caching
    http.headers().cacheControl();

    http.csrf().disable().authorizeRequests().
        antMatchers("/").permitAll().
        antMatchers(HttpMethod.POST, "/login").
        permitAll().antMatchers("/health/**").hasRole("APP_ADMIN").
        anyRequest().
        authenticated().
        and().
        addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class).
        addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean() public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override() protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource).
        usersByUsernameQuery(USER_NAME_BY_USER_NAME_QUERY).
        authoritiesByUsernameQuery(AUTHORITIES_BY_USER_NAME_QUERY)
        .passwordEncoder(new BCryptPasswordEncoder(12));
  }
}
