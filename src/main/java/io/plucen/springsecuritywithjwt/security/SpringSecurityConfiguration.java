package io.plucen.springsecuritywithjwt.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.springsecuritywithjwt.security.jwt.JwtAuthenticationFilter;
import io.plucen.springsecuritywithjwt.security.jwt.JwtCreationFilter;
import io.plucen.springsecuritywithjwt.users.UserService;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final ObjectMapper objectMapper;
  private final SecretKey jwtSecretKey;

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(userService);
    return daoAuthenticationProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .addFilter(new JwtCreationFilter(authenticationManager(), jwtSecretKey, objectMapper))
        .addFilterAfter(
            new JwtAuthenticationFilter(authenticationManager(), jwtSecretKey, userService),
            JwtCreationFilter.class)
        .sessionManagement()
        .sessionCreationPolicy(STATELESS)
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated();
  }
}
