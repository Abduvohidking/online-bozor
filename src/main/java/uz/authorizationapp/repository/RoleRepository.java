package uz.authorizationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.authorizationapp.entity.Roles;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Set<Roles> getByName(String name);
}
