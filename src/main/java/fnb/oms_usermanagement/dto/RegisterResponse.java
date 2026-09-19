package fnb.oms_usermanagement.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long customerId;
    private String firstName;
    private String surname;
    private String email;
    private String role;
    private String message;
}