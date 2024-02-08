package uz.authorizationapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.authorizationapp.entity.User;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    AuditorAware<User> auditorAware(){
        return new SpringSecurityAuditAwareImpl();
    }
}
