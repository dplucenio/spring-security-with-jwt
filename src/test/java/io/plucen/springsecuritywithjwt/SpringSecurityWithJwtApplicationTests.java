package io.plucen.springsecuritywithjwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.plucen.springsecuritywithjwt.users.UserService;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SpringSecurityWithJwtApplicationTests {

  @Autowired MockMvc mockMvc;
  @Autowired UserService userService;
  @Autowired DataSource dataSource;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Test
  void shouldNotAuthorizeUnauthenticatedAccess() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isUnauthorized());
  }

  @Test
  void shouldAuthorizeWithBasicAuthentication() throws Exception {
    System.out.println(userService.findAll());
    mockMvc
        .perform(
            get("/users")
                .header(
                    HttpHeaders.AUTHORIZATION,
                    "Basic "
                        + Base64Utils.encodeToString(
                            (adminEmail + ":" + adminPassword).getBytes())))
        .andExpect(status().isOk());
  }
}
