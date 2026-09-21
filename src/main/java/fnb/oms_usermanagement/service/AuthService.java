package fnb.oms_usermanagement.service;

import fnb.oms_usermanagement.dto.LoginRequest;
import fnb.oms_usermanagement.dto.LoginResponse;
import fnb.oms_usermanagement.dto.RegisterRequest;
import fnb.oms_usermanagement.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);  // ← only once
    LoginResponse login(LoginRequest request);           // ← only once
}