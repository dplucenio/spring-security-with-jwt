package io.plucen.springsecuritywithjwt;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.springsecuritywithjwt.users.UserController.UserCreationDto;
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
  @Autowired ObjectMapper objectMapper;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Test
  void shouldNotAuthorizeUnauthenticatedAccess() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isUnauthorized());
  }

  @Test
  void shouldReturnJwtAfterSuccessfulAuthentication() throws Exception {
    mockMvc
        .perform(
            post("/login")
                .contentType(APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(
                        new UserCreationDto(adminEmail, adminPassword))))
        .andExpect(status().isOk())
        .andExpect(header().exists("Authorization"));

    final String token =
        mockMvc
            .perform(
                post("/login")
                    .contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            new UserCreationDto(adminEmail, adminPassword))))
            .andReturn()
            .getResponse()
            .getHeader("Authorization")
            .replace("Bearer ", "");
    System.out.println(token);
  }

  @Test
  void shouldAuthorizeWithBasicAuthentication() throws Exception {
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
