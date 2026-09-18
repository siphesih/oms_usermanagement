package fnb.oms_usermanagement.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@jakarta.persistence.Entity
@Table(name="users")
public class Entity {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int customer_id	;
    private  String  first_name;
    private  String surname;
    private   String email;
    private   String role;
    private  String updated_at;



}
