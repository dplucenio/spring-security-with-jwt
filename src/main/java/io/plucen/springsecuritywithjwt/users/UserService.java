package io.plucen.springsecuritywithjwt.users;

import io.plucen.springsecuritywithjwt.users.User.UserDetailsAdapter;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  public User create(String email, String password) {
    return userRepository.insert(new User(UUID.randomUUID(), email, password));
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("a"));
    return new UserDetailsAdapter(user);
  }
}
