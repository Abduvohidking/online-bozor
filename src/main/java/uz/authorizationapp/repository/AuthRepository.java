package uz.authorizationapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.authorizationapp.entity.User;

public interface AuthRepository extends JpaRepository<User,Long> {
    boolean existsByUsernameAndState(String username, Integer state);
    User findByUsername(String username);
}
