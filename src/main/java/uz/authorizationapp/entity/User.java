package uz.authorizationapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.authorizationapp.entity.template.ConfigEntity;
import uz.authorizationapp.enums.Permission;
import uz.authorizationapp.enums.UserType;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "auth_users")
@EntityListeners(AuditingEntityListener.class)
public class User extends ConfigEntity implements UserDetails {


    public User(String fullName, String username, String password,Set<Roles> roles) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    @Column(nullable = false, length = 300)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private Date birthDate;

    private String gender;

    private String phone_number;

    private Long oblId;

    private Long areaId;

    private Long districtId;

    @Enumerated(value = EnumType.STRING)
    private UserType userType = UserType.USER;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;

    @JsonIgnore
    private boolean accountNonExpired = true;

    @JsonIgnore
    private boolean accountNonLocked = true;

    @JsonIgnore
    private boolean credentialsNonExpired = true;

    @JsonIgnore
    private boolean enabled = true;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Permission[] values = Permission.values();
        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission value : values) {
            grantedAuthorities.add(new SimpleGrantedAuthority(value.name()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
