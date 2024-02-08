package uz.authorizationapp.conf;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import reactor.core.publisher.Mono;
import uz.authorizationapp.entity.Roles;
import uz.authorizationapp.entity.User;
import uz.authorizationapp.enums.Permission;
import uz.authorizationapp.utils.AppConstants;


import java.util.*;

public class SpringSecurityAuditAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            return Optional.of(user);
        }
        return Optional.empty();
    }

}
