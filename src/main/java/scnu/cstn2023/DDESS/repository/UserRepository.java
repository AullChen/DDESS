package scnu.cstn2023.DDESS.repository;

import scnu.cstn2023.DDESS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}