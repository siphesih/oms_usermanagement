package fnb.oms_usermanagement.controller;

import fnb.oms_usermanagement.dto.LoginRequest;
import fnb.oms_usermanagement.dto.LoginResponse;
import fnb.oms_usermanagement.dto.RegisterRequest;
import fnb.oms_usermanagement.dto.RegisterResponse;
import fnb.oms_usermanagement.security.JwtTokenProvider;
import fnb.oms_usermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);

        // Generate JWT token now that login succeeded
        String token = jwtTokenProvider.generateToken(
                response.getCustomerId(),
                response.getEmail(),
                response.getRole()
        );

        response.setToken(token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok("You are authenticated as: " + email);
    }
    
}