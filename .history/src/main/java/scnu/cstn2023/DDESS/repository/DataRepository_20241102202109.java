package scnu.cstn2023.DDESS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByUser(User user);

    Optional<Data> findByIdAndUser(Long id, User user);
}