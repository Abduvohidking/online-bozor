package uz.authorizationapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.authorizationapp.entity.Tokens;
import uz.authorizationapp.entity.User;
import uz.authorizationapp.repository.AuthRepository;
import uz.authorizationapp.repository.TokenRepository;

import java.util.UUID;

@Component
public class UUIDGenerate {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    TokenRepository tokenRepository;

    public String generateUUID(String username) {
        User user = authRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("user " + username + " not found");
        }
        String token =  UUID.randomUUID().toString();
        Tokens tokens = new Tokens(
                true,
                token,
                user
        );

        tokenRepository.save(tokens);
        return token;
    }
}
