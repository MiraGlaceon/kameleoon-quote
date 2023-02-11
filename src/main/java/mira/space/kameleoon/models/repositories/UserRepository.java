package mira.space.kameleoon.models.repositories;

import mira.space.kameleoon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select email from User u where u.email = ?1")
    Optional<String> findEmail(String email);

    @Query("select password from User u where u.password = ?1")
    Optional<String> findPassword(String password);

    Optional<User> findBySessionId(String sessionId);
}
