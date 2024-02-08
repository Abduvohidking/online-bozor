package uz.authorizationapp.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.authorizationapp.entity.Roles;
import uz.authorizationapp.entity.User;
import uz.authorizationapp.enums.Permission;
import uz.authorizationapp.repository.AuthRepository;
import uz.authorizationapp.repository.RoleRepository;
import uz.authorizationapp.utils.AppConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthRepository authRepository;



    @Value("${spring.sql.init.mode}")
    private String initModeType;

    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if (initModeType.equals("always")) {
            Permission[] permission = Permission.values();
            Roles admin = roleRepository.save(
                    new Roles(
                            AppConstants.ADMIN,
                            List.of(permission)
                    )
            );
            Roles user = roleRepository.save(
                    new Roles(
                            AppConstants.USER,
                            List.of(Permission.USER_ROLE)
                    )
            );
            Set<Roles> roles = new HashSet<Roles>();
            roles.add(admin);
            authRepository.save(
                    new User(
                            "Admin",
                            "admin",
                            passwordEncoder().encode("admin123"),
                            roles
                    )
            );

        }
    }
}
