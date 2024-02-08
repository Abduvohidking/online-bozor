package uz.authorizationapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.authorizationapp.entity.template.ConfigEntity;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tokens extends ConfigEntity {
    private final long expire = 1000 * 60 * 60 * 24;
    public Tokens(boolean active, String token, User user) {
        this.active = active;
        this.token = token;
        this.user = user;
    }

    private boolean active;

    private String token;

    @ManyToOne
    private User user;

    private Timestamp expiration = new Timestamp(System.currentTimeMillis()+expire);
}
