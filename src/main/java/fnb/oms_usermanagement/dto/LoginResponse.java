package fnb.oms_usermanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
    private Long customerId;
    private String firstName;
    private String email;
    private String role;
    private String message;
}