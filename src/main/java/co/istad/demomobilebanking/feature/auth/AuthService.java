package co.istad.demomobilebanking.feature.auth;

import co.istad.demomobilebanking.feature.auth.dto.AuthResponse;
import co.istad.demomobilebanking.feature.auth.dto.ChangePasswordRequest;
import co.istad.demomobilebanking.feature.auth.dto.LoginRequest;
import co.istad.demomobilebanking.feature.auth.dto.RefreshTokenRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
    void changePassword(Jwt jwt, ChangePasswordRequest changePasswordRequest);
}
