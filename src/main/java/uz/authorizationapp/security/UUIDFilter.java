package uz.authorizationapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.authorizationapp.entity.Tokens;
import uz.authorizationapp.repository.TokenRepository;

import java.io.IOException;

@Component
public class UUIDFilter extends OncePerRequestFilter {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            Tokens byToken = tokenRepository.findByTokenAndActive(token,true);
            if (byToken != null) {
                if(byToken.getExpiration().getTime() > System.currentTimeMillis()) {
                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(byToken.getUser(), null,
                                            byToken.getUser().getAuthorities()));
                }else {
                    byToken.setActive(false);
                    tokenRepository.save(byToken);
                }

            }
        }
        filterChain.doFilter(request,response);
    }
}
