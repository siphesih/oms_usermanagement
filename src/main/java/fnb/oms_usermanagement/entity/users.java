package fnb.oms_usermanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@jakarta.persistence.Entity
@Table(name="users")
public class users {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int customer_id	;
    private  String  first_name;
    private  String surname;
    private  String email;
    private  String role;
    private  String updated_at;

    @PrePersist
    protected void prePersist()
    {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void prePersist(Object obj)
    {
        this.updatedAt = LocalDateTime.now();
    }

}


