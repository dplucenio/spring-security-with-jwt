package io.plucen.springsecuritywithjwt.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtCreationFilter extends UsernamePasswordAuthenticationFilter {

  private final ObjectMapper objectMapper;
  private final SecretKey jwtSecretKey;

  public JwtCreationFilter(
      AuthenticationManager authenticationManager,
      SecretKey jwtSecretKey,
      ObjectMapper objectMapper) {
    super(authenticationManager);
    this.objectMapper = objectMapper;
    this.jwtSecretKey = jwtSecretKey;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      final UserAuthenticationDto userAuthenticationDto =
          objectMapper.readValue(request.getInputStream(), UserAuthenticationDto.class);
      return getAuthenticationManager()
          .authenticate(
              new UsernamePasswordAuthenticationToken(
                  userAuthenticationDto.getEmail(), userAuthenticationDto.getPassword()));
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    final String token =
        Jwts.builder()
            .setSubject(authResult.getName())
            .signWith(jwtSecretKey)
            .setIssuedAt(Date.valueOf(LocalDate.now()))
            .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
            .compact();
    response.addHeader("Authorization", "Bearer " + token);
  }

  @Data
  public static class UserAuthenticationDto {
    private final String email;
    private final String password;
  }
}
