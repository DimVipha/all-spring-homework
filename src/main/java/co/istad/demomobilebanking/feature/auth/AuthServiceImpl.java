package co.istad.demomobilebanking.feature.auth;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.auth.dto.AuthResponse;
import co.istad.demomobilebanking.feature.auth.dto.ChangePasswordRequest;
import co.istad.demomobilebanking.feature.auth.dto.LoginRequest;
import co.istad.demomobilebanking.feature.auth.dto.RefreshTokenRequest;
import co.istad.demomobilebanking.feature.token.TokenService;
import co.istad.demomobilebanking.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HttpSecurity httpSecurity;
    private  final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenService tokenService;

    @Override
    public AuthResponse refresh(RefreshTokenRequest refreshTokenRequest) {

        Authentication auth = new BearerTokenAuthenticationToken(
                refreshTokenRequest.refreshToken()
        );

        auth = jwtAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);
    }

    @Override
    public void changePassword(Jwt jwt, ChangePasswordRequest changePasswordRequest) {
        User user=userRepository.findAllByPhoneNumber(
                jwt.getId()).orElseThrow(
                ()->  new ResponseStatusException(
                           HttpStatus.NOT_FOUND, "user has not been found"
                   )
        );
        if (!changePasswordRequest.password().equals(changePasswordRequest.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not match");
        }
        if (!passwordEncoder.matches(changePasswordRequest.oldPassword(),user.getPassword())){
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "old password do not match"
            );
        }

        if (passwordEncoder.matches(changePasswordRequest.password(), user.getPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "new password is the same with old password"
            );
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.password()));
        userRepository.save(user);
        log.info(" password for user {}", user.getPassword());
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );

        auth = daoAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);
    }

}

