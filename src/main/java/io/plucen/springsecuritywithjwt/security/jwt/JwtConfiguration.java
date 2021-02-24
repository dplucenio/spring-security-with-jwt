package io.plucen.springsecuritywithjwt.security.jwt;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

  @Bean
  public SecretKey jwtSecretKey(@Value("${jwtSecret}") String jwtSecret) {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }
}
