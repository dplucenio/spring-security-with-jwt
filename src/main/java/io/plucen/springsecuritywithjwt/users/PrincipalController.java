package io.plucen.springsecuritywithjwt.users;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

  @GetMapping("/user")
  public Object user(Principal principal) {
    System.out.println(principal);
    return principal;
  }
}
