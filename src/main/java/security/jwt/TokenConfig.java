package security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by sangeet on 3/9/2017.
 */
@Component()public class TokenConfig {
  public static final String SECRET_KEY = "${token.secretkey}";
  public static final String EXPIRATION_TIME_KEY = "${token.expirationtime}";

  public static String secretKey;
  public static Long expirationTime;

  public TokenConfig(@Value(TokenConfig.SECRET_KEY) final String secretKey,
      @Value(TokenConfig.EXPIRATION_TIME_KEY) final Long expirationTime) {
    TokenConfig.secretKey = secretKey;
    TokenConfig.expirationTime = expirationTime;
  }

  public static String getSecretKey() {
    return secretKey;
  }

  public static Long getExpirationTime() {
    return expirationTime;
  }
}
