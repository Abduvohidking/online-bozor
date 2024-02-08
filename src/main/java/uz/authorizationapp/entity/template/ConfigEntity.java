package uz.authorizationapp.entity.template;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.authorizationapp.entity.User;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class ConfigEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer state = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false,nullable = false)
    private Date CreatedAt;


    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date UpdatedAt;

    @JoinColumn(updatable = false,name = "created_by")
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;


    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updateBy;
}
