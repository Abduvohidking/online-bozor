package uz.authorizationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.authorizationapp.entity.Tokens;
import uz.authorizationapp.entity.User;

public interface TokenRepository extends JpaRepository<Tokens,Long> {
    Tokens findByTokenAndActive(String token,boolean active);
    Tokens findByUserIdAndActive(Long userId,boolean active);
}
