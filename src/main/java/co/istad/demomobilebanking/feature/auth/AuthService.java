package co.istad.demomobilebanking.feature.auth;

import co.istad.demomobilebanking.feature.auth.dto.AuthResponse;
import co.istad.demomobilebanking.feature.auth.dto.LoginRequest;
import co.istad.demomobilebanking.feature.auth.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
}
