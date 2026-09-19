package fnb.oms_usermanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
}