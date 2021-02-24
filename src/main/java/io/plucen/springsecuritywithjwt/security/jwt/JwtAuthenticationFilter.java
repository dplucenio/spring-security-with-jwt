package io.plucen.springsecuritywithjwt.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  private final SecretKey jwtSecretKey;

  public JwtAuthenticationFilter(
      AuthenticationManager authenticationManager, SecretKey jwtSecretKey) {
    super(authenticationManager);
    this.jwtSecretKey = jwtSecretKey;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    final String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null
        || authorizationHeader.isEmpty()
        || !authorizationHeader.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }
    final String token = authorizationHeader.replace("Bearer", "");
    final Claims body =
        Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody();
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(body.getSubject(), null, null));
    chain.doFilter(request, response);
  }
}
