package fnb.oms_usermanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@jakarta.persistence.Entity
@Table(name="user_credentials")
public class user_credentials {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long credential_id;

    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private int   customer_id ;
    private String password_hash ;
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
    }

}
