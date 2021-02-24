package io.plucen.springsecuritywithjwt;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.springsecuritywithjwt.users.UserController.UserCreationDto;
import io.plucen.springsecuritywithjwt.users.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SpringSecurityWithJwtApplicationTests {

  @Autowired MockMvc mockMvc;
  @Autowired UserService userService;
  @Autowired ObjectMapper objectMapper;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Test
  void shouldNotAuthorizeUnauthenticatedAccess() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isForbidden());
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
  }

  @Test
  public void shouldBeAbleToAccessProtectedEndpoint() throws Exception {
    final String token = fetchJwt(adminEmail, adminPassword);

    mockMvc
        .perform(get("/users").header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldBeAbleToRetrieveAuthenticatedUser() throws Exception {
    final String token = fetchJwt(adminEmail, adminPassword);
    final String id = userService.findByEmail(adminEmail).orElseThrow().getId().toString();
    mockMvc
        .perform(get("/user").header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.details.id", is(id)));
  }

  private String fetchJwt(String adminEmail, String adminPassword) throws Exception {
    return mockMvc
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
  }
}
