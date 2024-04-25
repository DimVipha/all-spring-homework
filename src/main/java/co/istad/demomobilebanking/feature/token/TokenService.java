package co.istad.demomobilebanking.feature.token;

import co.istad.demomobilebanking.feature.auth.dto.AuthResponse;
import org.springframework.security.core.Authentication;

public interface TokenService {
    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}
