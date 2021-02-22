package io.plucen.springsecuritywithjwt;

import io.plucen.springsecuritywithjwt.users.UserService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityWithJwtApplication {

  private final UserService userService;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityWithJwtApplication.class, args);
  }

  @PostConstruct
  public void setAdminUser() {
    if (userService.findByEmail(adminEmail).isEmpty()) {
      userService.create(adminEmail, adminPassword);
      System.out.println("Created admin user");
    } else {
      System.out.println("already created");
    }
  }
}
