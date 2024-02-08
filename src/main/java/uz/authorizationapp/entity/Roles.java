package uz.authorizationapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import uz.authorizationapp.enums.Permission;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Roles {
    @Id
    @GeneratedValue
    private Long id;

    public Roles(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Permission> permissions;
}
