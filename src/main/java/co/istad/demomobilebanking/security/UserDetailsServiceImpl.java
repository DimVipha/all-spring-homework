package co.istad.demomobilebanking.security;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user=userRepository.findAllByPhoneNumber(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("User not found !!! " )
        );

        CustomUserDetails customUserDetail=new CustomUserDetails();
        customUserDetail.setUser(user);

        log.info("user: {}",user);


        return customUserDetail;
    }
}
