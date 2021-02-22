package io.plucen.springsecuritywithjwt.users;

import io.plucen.springsecuritywithjwt.withinsert.WithInsert;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID>, WithInsert<User> {

  @Query("select * from application_user where email = :email")
  Optional<User> findByEmail(String email);
}
