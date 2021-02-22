package io.plucen.springsecuritywithjwt.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public Iterable<User> index() {
    return userService.findAll();
  }

  @PostMapping
  public User insert(@RequestBody UserCreationDto user) {
    return userService.create(user.getEmail(), user.getPassword());
  }

  @Data
  public static class UserCreationDto {
    private final String email;
    private final String password;
  }
}
