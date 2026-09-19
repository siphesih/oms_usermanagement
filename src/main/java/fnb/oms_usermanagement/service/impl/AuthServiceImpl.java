/*package fnb.oms_usermanagement.service.impl;


import fnb.oms_usermanagement.dto.LoginRequest;
import fnb.oms_usermanagement.dto.LoginResponse;
import fnb.oms_usermanagement.dto.RegisterRequest;
import fnb.oms_usermanagement.dto.RegisterResponse;
import fnb.oms_usermanagement.repository.UserCredentialsRepository;
import fnb.oms_usermanagement.repository.UserRepository;
import fnb.oms_usermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        User user = User.builder();






        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}*/


package fnb.oms_usermanagement.service.impl;

import fnb.oms_usermanagement.dto.LoginRequest;
import fnb.oms_usermanagement.dto.LoginResponse;
import fnb.oms_usermanagement.dto.RegisterRequest;
import fnb.oms_usermanagement.dto.RegisterResponse;
import fnb.oms_usermanagement.entity.Role;
import fnb.oms_usermanagement.entity.User;
import fnb.oms_usermanagement.entity.UserCredentials;
import fnb.oms_usermanagement.repository.UserCredentialsRepository;
import fnb.oms_usermanagement.repository.UserRepository;
import fnb.oms_usermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // 1. Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        // 2. Check passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        // 3. Create and save User
        User user = User.builder()
                .firstName(request.getFirstName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        // 4. Hash password and save credentials
        UserCredentials credentials = UserCredentials.builder()
                .user(user)
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        userCredentialsRepository.save(credentials);

        // 5. Return response
        return RegisterResponse.builder()
                .customerId(user.getCustomerId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Registration successful.")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // 1. Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));

        // 2. Find credentials
        UserCredentials credentials = userCredentialsRepository
                .findByUserCustomerId(user.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));

        // 3. Verify password
        if (!passwordEncoder.matches(request.getPassword(), credentials.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password.");
        }

        // 4. Return response (JWT token added later in security step)
        return LoginResponse.builder()
                .customerId(user.getCustomerId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Login successful.")
                .build();
    }
}
